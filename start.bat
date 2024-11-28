@echo off

:: Inicia el servidor de HSQLDB
echo Iniciando la base de datos HSQLDB...
start java -cp "lib\hsqldb-2.7.2.jar;." org.hsqldb.Server -port 9013 -database.0 file:templateDB -dbname.0 templateDB

:: Espera unos segundos para que el servidor inicie
echo Esperando 5 segundos para que la base de datos se inicie...
timeout /t 5 > nul

:: Verificar si el archivo .exe existe antes de intentar ejecutarlo
if exist "%~dp0preguntados-0.0.1-SNAPSHOT.exe" (
    echo Iniciando la aplicación...
    start "" "%~dp0preguntados-0.0.1-SNAPSHOT.exe"
) else (
    echo ERROR: No se encuentra el archivo preguntados-0.0.1-SNAPSHOT.exe en %~dp0
    pause
    exit /b 1
)

:: Evita que el script entre en un bucle inesperado
echo Todo listo. Cerrando el script principal...
pause
exit
