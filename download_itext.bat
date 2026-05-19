@echo off
echo Baixando biblioteca iText para gerar PDF...

powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/itextpdf/itextpdf/5.5.13.3/itextpdf-5.5.13.3.jar' -OutFile 'lib\itextpdf-5.5.13.3.jar'"

if exist "lib\itextpdf-5.5.13.3.jar" (
    echo ✅ iText baixado com sucesso!
) else (
    echo ❌ Erro ao baixar iText
)

pause
