@echo off
echo Baixando biblioteca QR Code Generator...

if not exist "lib" mkdir lib

echo Baixando QR Code Generator...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/github/kenglxn/qrgen/javase/2.6.0/javase-2.6.0.jar' -OutFile 'lib\qrgen-javase-2.6.0.jar'"

echo Baixando ZXing Core...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/google/zxing/core/3.5.1/core-3.5.1.jar' -OutFile 'lib\zxing-core-3.5.1.jar'"

echo ✅ Bibliotecas baixadas com sucesso!
echo 📍 Agora compile novamente o projeto

pause