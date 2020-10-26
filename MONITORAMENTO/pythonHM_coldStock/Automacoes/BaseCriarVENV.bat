echo "Filtrando arquivos..."
pause
wsl sed $'s/\r$//' BaseCriadorVENV > CriadorVENV
wsl sed $'s/\r$//' BaseAtivadorLinux > AtivadorLinux

echo "Criar VENV"
pause
wsl source CriadorVENV

pause