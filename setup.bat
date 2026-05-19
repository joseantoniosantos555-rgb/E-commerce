@echo off
echo ========================================
echo    E-COMMERCE JAVA - SETUP AUTOMATICO
echo ========================================
echo.

echo 1. Verificando Java...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java nao encontrado!
    echo    Instale o JDK 8+ em: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
) else (
    echo ✅ Java encontrado!
)

echo.
echo 2. Verificando PostgreSQL...
psql --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ⚠️  PostgreSQL nao encontrado no PATH
    echo    Certifique-se que esta instalado e rodando
) else (
    echo ✅ PostgreSQL encontrado!
)

echo.
echo 3. Verificando estrutura do projeto...
if not exist "lib\postgresql-42.7.3.jar" (
    echo ❌ Biblioteca PostgreSQL nao encontrada!
    echo    Arquivo necessario: lib\postgresql-42.7.3.jar
    pause
    exit /b 1
) else (
    echo ✅ Biblioteca PostgreSQL encontrada!
)

if not exist "bin" mkdir bin
echo ✅ Pasta bin criada/verificada!

echo.
echo 4. Compilando projeto...
call compile.bat
if %errorlevel% neq 0 (
    echo ❌ Erro na compilacao!
    pause
    exit /b 1
)

echo.
echo ========================================
echo ✅ SETUP CONCLUIDO COM SUCESSO!
echo ========================================
echo.
echo Para executar: run.bat
echo.
echo Usuarios para teste:
echo   Admin: admin / admin123
echo   Cliente: cliente / 123456
echo.
pause