@echo off
echo Baixando biblioteca ZXing para QR Code...

if not exist "lib" mkdir lib

echo Baixando ZXing Core...
curl -L -o "lib\zxing-core-3.5.1.jar" "https://repo1.maven.org/maven2/com/google/zxing/core/3.5.1/core-3.5.1.jar"

echo Baixando ZXing JavaSE...
curl -L -o "lib\zxing-javase-3.5.1.jar" "https://repo1.maven.org/maven2/com/google/zxing/javase/3.5.1/javase-3.5.1.jar"

echo ✅ Bibliotecas ZXing baixadas com sucesso!
echo 📍 Agora compile novamente o projeto

pause