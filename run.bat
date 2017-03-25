@echo off
set JAVA_OPTS=

if NOT "%1"=="debug" goto :START
set JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=0.0.0.0:8000

:START
start java %JAVA_OPTS% -jar arvore-ctt.jar agenda.Aplicacao

