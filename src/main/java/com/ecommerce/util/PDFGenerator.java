package com.ecommerce.util;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PDFGenerator {
    
    public static File generateInvoice(Order order) throws Exception {
        File pdfDir = new File("pdfs");
        if (!pdfDir.exists()) {
            pdfDir.mkdirs();
        }
        
        String fileName = "Nota_Fiscal_Pedido_" + order.getId() + ".pdf";
        File pdfFile = new File(pdfDir, fileName);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String clienteNome = order.getUser() != null ? order.getUser().getNomeCompleto() : "Cliente " + order.getUserId();
        
        StringBuilder content = new StringBuilder();
        List<String> objects = new ArrayList<>();
        
        content.append("%PDF-1.4\n");
        
        int obj1Pos = content.length();
        content.append("1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n");
        objects.add(String.valueOf(obj1Pos));
        
        int obj2Pos = content.length();
        content.append("2 0 obj\n<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n");
        objects.add(String.valueOf(obj2Pos));
        
        int obj3Pos = content.length();
        content.append("3 0 obj\n<< /Type /Page /Parent 2 0 R /Resources 4 0 R /MediaBox [0 0 595 842] /Contents 5 0 R >>\nendobj\n");
        objects.add(String.valueOf(obj3Pos));
        
        int obj4Pos = content.length();
        content.append("4 0 obj\n<< /Font << /F1 << /Type /Font /Subtype /Type1 /BaseFont /Helvetica-Bold >> /F2 << /Type /Font /Subtype /Type1 /BaseFont /Helvetica >> >> >>\nendobj\n");
        objects.add(String.valueOf(obj4Pos));
        
        StringBuilder stream = new StringBuilder();
        
        // Borda externa
        stream.append("0.5 w\n");
        stream.append("50 50 495 742 re S\n");
        
        // Cabeçalho - NF-e
        stream.append("50 770 495 22 re S\n");
        stream.append("450 770 m 450 792 l S\n");
        stream.append("BT /F1 14 Tf 200 775 Td (NOTA FISCAL) Tj ET\n");
        stream.append("BT /F2 8 Tf 480 782 Td (NF-e) Tj ET\n");
        stream.append("BT /F2 7 Tf 455 774 Td (No ").append(String.format("%08d", order.getId())).append(") Tj ET\n");
        stream.append("BT /F2 7 Tf 455 766 Td (SERIE 001) Tj ET\n");
        
        // Linha horizontal
        stream.append("50 770 m 545 770 l S\n");
        stream.append("50 750 m 545 750 l S\n");
        
        // Dados do Emitente
        stream.append("BT /F1 9 Tf 55 735 Td (EMITENTE) Tj ET\n");
        stream.append("BT /F2 8 Tf 55 722 Td (E-COMMERCE LTDA) Tj ET\n");
        stream.append("BT /F2 7 Tf 55 712 Td (RUA DO COMERCIO, 123 - CENTRO) Tj ET\n");
        stream.append("BT /F2 7 Tf 55 702 Td (CNPJ: 12.345.678/0001-90) Tj ET\n");
        
        // Chave de acesso (simulada)
        stream.append("BT /F2 6 Tf 320 735 Td (CHAVE DE ACESSO) Tj ET\n");
        stream.append("BT /F2 7 Tf 320 725 Td (").append(String.format("%044d", order.getId() * 123456789L)).append(") Tj ET\n");
        
        stream.append("50 695 m 545 695 l S\n");
        
        // Dados do Destinatário
        stream.append("BT /F1 9 Tf 55 680 Td (DESTINATARIO / REMETENTE) Tj ET\n");
        stream.append("BT /F2 8 Tf 55 667 Td (NOME/RAZAO SOCIAL: ").append(clienteNome).append(") Tj ET\n");
        String endereco = order.getDeliveryAddress() != null ? order.getDeliveryAddress() : "Nao informado";
        stream.append("BT /F2 7 Tf 55 657 Td (ENDERECO: ").append(endereco).append(") Tj ET\n");
        
        stream.append("50 645 m 545 645 l S\n");
        
        // Dados da Nota
        stream.append("BT /F2 7 Tf 55 635 Td (DATA DE EMISSAO) Tj ET\n");
        stream.append("BT /F1 8 Tf 55 625 Td (").append(order.getOrderDate().format(formatter)).append(") Tj ET\n");
        
        stream.append("200 635 m 200 615 l S\n");
        stream.append("BT /F2 7 Tf 205 635 Td (PROTOCOLO DE AUTORIZACAO) Tj ET\n");
        stream.append("BT /F1 8 Tf 205 625 Td (").append(String.format("%015d", order.getId() * 999999L)).append(") Tj ET\n");
        
        stream.append("50 615 m 545 615 l S\n");
        
        // Tabela de Produtos
        stream.append("BT /F1 8 Tf 55 600 Td (DADOS DOS PRODUTOS / SERVICOS) Tj ET\n");
        stream.append("50 590 m 545 590 l S\n");
        
        // Cabeçalho da tabela
        stream.append("0.8 0.8 0.8 rg\n");
        stream.append("50 575 495 15 re f\n");
        stream.append("0 0 0 rg\n");
        
        stream.append("50 575 m 545 575 l S\n");
        stream.append("50 590 m 50 575 l S\n");
        stream.append("80 590 m 80 575 l S\n");
        stream.append("300 590 m 300 575 l S\n");
        stream.append("340 590 m 340 575 l S\n");
        stream.append("390 590 m 390 575 l S\n");
        stream.append("450 590 m 450 575 l S\n");
        stream.append("545 590 m 545 575 l S\n");
        
        stream.append("BT /F1 7 Tf 52 578 Td (COD) Tj ET\n");
        stream.append("BT /F1 7 Tf 85 578 Td (DESCRICAO DO PRODUTO) Tj ET\n");
        stream.append("BT /F1 7 Tf 305 578 Td (QTD) Tj ET\n");
        stream.append("BT /F1 7 Tf 345 578 Td (UN) Tj ET\n");
        stream.append("BT /F1 7 Tf 395 578 Td (VL UNIT) Tj ET\n");
        stream.append("BT /F1 7 Tf 455 578 Td (VL TOTAL) Tj ET\n");
        
        // Itens
        int yPos = 565;
        int itemNum = 1;
        for (OrderItem item : order.getItems()) {
            stream.append("50 ").append(yPos).append(" m 545 ").append(yPos).append(" l S\n");
            stream.append("50 ").append(yPos + 15).append(" m 50 ").append(yPos).append(" l S\n");
            stream.append("80 ").append(yPos + 15).append(" m 80 ").append(yPos).append(" l S\n");
            stream.append("300 ").append(yPos + 15).append(" m 300 ").append(yPos).append(" l S\n");
            stream.append("340 ").append(yPos + 15).append(" m 340 ").append(yPos).append(" l S\n");
            stream.append("390 ").append(yPos + 15).append(" m 390 ").append(yPos).append(" l S\n");
            stream.append("450 ").append(yPos + 15).append(" m 450 ").append(yPos).append(" l S\n");
            stream.append("545 ").append(yPos + 15).append(" m 545 ").append(yPos).append(" l S\n");
            
            stream.append("BT /F2 7 Tf 55 ").append(yPos + 3).append(" Td (").append(String.format("%03d", itemNum++)).append(") Tj ET\n");
            stream.append("BT /F2 7 Tf 85 ").append(yPos + 3).append(" Td (").append(item.getProduct().getNome()).append(") Tj ET\n");
            stream.append("BT /F2 7 Tf 310 ").append(yPos + 3).append(" Td (").append(item.getQuantity()).append(") Tj ET\n");
            stream.append("BT /F2 7 Tf 345 ").append(yPos + 3).append(" Td (UN) Tj ET\n");
            stream.append("BT /F2 7 Tf 395 ").append(yPos + 3).append(" Td (").append(item.getPrice()).append(") Tj ET\n");
            stream.append("BT /F2 7 Tf 455 ").append(yPos + 3).append(" Td (").append(item.getSubtotal()).append(") Tj ET\n");
            
            yPos -= 15;
        }
        
        stream.append("50 ").append(yPos).append(" m 545 ").append(yPos).append(" l S\n");
        
        // Totais
        yPos -= 20;
        stream.append("50 ").append(yPos).append(" m 545 ").append(yPos).append(" l S\n");
        
        stream.append("0.9 0.9 0.9 rg\n");
        stream.append("50 ").append(yPos - 20).append(" 495 20 re f\n");
        stream.append("0 0 0 rg\n");
        
        stream.append("BT /F1 10 Tf 55 ").append(yPos - 15).append(" Td (VALOR TOTAL DOS PRODUTOS) Tj ET\n");
        stream.append("BT /F1 11 Tf 450 ").append(yPos - 15).append(" Td (R$ ").append(order.getTotal()).append(") Tj ET\n");
        
        stream.append("50 ").append(yPos - 20).append(" m 545 ").append(yPos - 20).append(" l S\n");
        
        // Informações Adicionais
        yPos -= 40;
        stream.append("50 ").append(yPos).append(" m 545 ").append(yPos).append(" l S\n");
        stream.append("BT /F1 8 Tf 55 ").append(yPos - 12).append(" Td (INFORMACOES COMPLEMENTARES) Tj ET\n");
        stream.append("BT /F2 7 Tf 55 ").append(yPos - 24).append(" Td (Pedido: #").append(order.getId()).append(" | Status: ENTREGUE) Tj ET\n");
        stream.append("BT /F2 7 Tf 55 ").append(yPos - 36).append(" Td (Data de Geracao: ").append(java.time.LocalDateTime.now().format(formatter)).append(") Tj ET\n");
        String paymentMethod = order.getPaymentMethod() != null ? order.getPaymentMethod() : "Nao informado";
        paymentMethod = paymentMethod.replace("ã", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("á", "a").replace("ú", "u");
        stream.append("BT /F2 7 Tf 55 ").append(yPos - 48).append(" Td (Forma de Pagamento: ").append(paymentMethod).append(") Tj ET\n");
        stream.append("BT /F2 7 Tf 55 ").append(yPos - 60).append(" Td (Observacoes: Produto entregue conforme solicitado.) Tj ET\n");
        stream.append("BT /F2 7 Tf 55 ").append(yPos - 72).append(" Td (Garantia: 90 dias conforme Codigo de Defesa do Consumidor) Tj ET\n");
        stream.append("BT /F2 7 Tf 55 ").append(yPos - 84).append(" Td (SAC: 0800-123-4567 | Email: sac@ecommerce.com.br) Tj ET\n");
        
        yPos -= 100;
        stream.append("50 ").append(yPos).append(" m 545 ").append(yPos).append(" l S\n");
        
        stream.append("BT /F2 6 Tf 55 ").append(yPos - 10).append(" Td (Documento emitido por computador. Valido sem assinatura.) Tj ET\n");
        stream.append("BT /F2 6 Tf 380 ").append(yPos - 10).append(" Td (www.ecommerce.com.br) Tj ET\n");
        
        String streamContent = stream.toString();
        
        int obj5Pos = content.length();
        content.append("5 0 obj\n<< /Length ").append(streamContent.length()).append(" >>\nstream\n");
        content.append(streamContent);
        content.append("\nendstream\nendobj\n");
        objects.add(String.valueOf(obj5Pos));
        
        int xrefPos = content.length();
        content.append("xref\n");
        content.append("0 6\n");
        content.append("0000000000 65535 f \n");
        for (String pos : objects) {
            content.append(String.format("%010d 00000 n \n", Integer.parseInt(pos)));
        }
        
        content.append("trailer\n<< /Size 6 /Root 1 0 R >>\n");
        content.append("startxref\n");
        content.append(xrefPos).append("\n");
        content.append("%%EOF\n");
        
        FileOutputStream fos = new FileOutputStream(pdfFile);
        fos.write(content.toString().getBytes("ISO-8859-1"));
        fos.close();
        
        return pdfFile;
    }
}
