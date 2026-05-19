@echo off
echo Iniciando E-Commerce...

REM Executar com a estrutura correta
java -cp "bin;lib\postgresql-42.7.3.jar" com.ecommerce.ECommerceApp

pause