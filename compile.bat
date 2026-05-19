@echo off
echo Compilando E-Commerce Java...

if not exist "bin" mkdir bin

REM Compilar considerando a estrutura REAL do projeto
javac -d bin -cp "src;lib\postgresql-42.7.3.jar;lib\zxing-core-3.5.1.jar;lib\zxing-javase-3.5.1.jar" src\main\java\com.ecommerce\**\*.java

if %errorlevel% == 0 (
    echo ✅ Compilacao concluida com sucesso!
    echo 🚀 Para executar: run.bat
) else (
    echo ❌ Erro na compilacao!
    echo 📍 Verifique a estrutura de pastas
)

pause