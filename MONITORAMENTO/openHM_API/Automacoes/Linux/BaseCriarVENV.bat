echo "Filtrando arquivos..."
pause
sed $'s/\r$//' BaseCriadorVENV > CriadorVENV
sed $'s/\r$//' BaseAtivadorLinux > AtivadorLinux

echo "Criar VENV"
pause
wsl source CriadorVENV

pause