package com.ecommerce.controller;

import com.ecommerce.entity.*;
import com.ecommerce.repository.*;
import com.ecommerce.service.CarrinhoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public String checkout(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            return "redirect:/login?redirect=/checkout";
        }
        
        if (carrinhoService.getItens(session).isEmpty()) {
            return "redirect:/carrinho";
        }
        
        model.addAttribute("itens", carrinhoService.getItens(session));
        model.addAttribute("total", carrinhoService.getTotal(session));
        model.addAttribute("usuario", usuario);
        
        return "checkout";
    }

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @PostMapping("/finalizar")
    public String finalizarPedido(@RequestParam String cep,
                                 @RequestParam String endereco,
                                 @RequestParam String cidade,
                                 @RequestParam String uf,
                                 @RequestParam(required = false) String complemento,
                                 @RequestParam String tipoPagamento,
                                 @RequestParam(required = false) String numeroCartao,
                                 @RequestParam(required = false) String parcelas,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            return "redirect:/login?redirect=/checkout";
        }
        
        List<CarrinhoService.ItemCarrinho> itens = carrinhoService.getItens(session);
        if (itens.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Carrinho vazio!");
            return "redirect:/carrinho";
        }
        
        try {
            // Criar pedido
            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);
            pedido.setDataPedido(LocalDateTime.now());
            pedido.setEnderecoEntrega(endereco + ", " + cidade + " - " + uf + " CEP: " + cep);
            pedido.setStatusPedido(Pedido.StatusPedido.AGUARDANDO);
            
            BigDecimal total = carrinhoService.getTotal(session);
            BigDecimal frete = calcularFrete(uf);
            BigDecimal desconto = "PIX".equals(tipoPagamento) ? total.multiply(new BigDecimal("0.05")) : BigDecimal.ZERO;
            
            pedido.setValorFrete(frete);
            pedido.setValorTotal(total.add(frete).subtract(desconto));
            
            pedido = pedidoRepository.save(pedido);
            
            // Criar itens do pedido
            for (CarrinhoService.ItemCarrinho item : itens) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setPedido(pedido);
                itemPedido.setProduto(item.getProduto());
                itemPedido.setQuantidade(item.getQuantidade());
                itemPedido.setPrecoUnitario(item.getProduto().getPrecoUnitario());
                itemPedido.setValorTotal(item.getSubtotal());
                itemPedido.setTamanho(item.getTamanho());
                itemPedido.setCor(item.getCor());
                
                itemPedidoRepository.save(itemPedido);
            }
            
            // Criar pagamento
            Pagamento pagamento = new Pagamento();
            pagamento.setPedido(pedido);
            pagamento.setTipoPagamento(Pagamento.TipoPagamento.valueOf(tipoPagamento));
            pagamento.setValor(pedido.getValorTotal());
            pagamento.setStatusPagamento(Pagamento.StatusPagamento.PENDENTE);
            
            if ("CARTAO".equals(tipoPagamento)) {
                pagamento.setCartaoUltimosDigitos(numeroCartao.substring(numeroCartao.length() - 4));
            }
            
            pagamentoRepository.save(pagamento);
            
            carrinhoService.limparCarrinho(session);
            
            return "redirect:/pedidos/" + pedido.getIdPedido() + "/acompanhar";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao finalizar pedido: " + e.getMessage());
            return "redirect:/carrinho";
        }
    }
    
    private BigDecimal calcularFrete(String uf) {
        return switch (uf) {
            case "SP" -> new BigDecimal("15.00");
            case "RJ" -> new BigDecimal("18.00");
            case "MG" -> new BigDecimal("20.00");
            case "RS" -> new BigDecimal("25.00");
            case "PR" -> new BigDecimal("22.00");
            case "SC" -> new BigDecimal("24.00");
            default -> new BigDecimal("30.00");
        };
    }
}