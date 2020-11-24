echo "Filtrando arquivos..."
pause
sed $'s/\r$//' BaseCriadorVENV > CriadorVENV
sed $'s/\r$//' BaseAtivadorLinux > AtivadorLinux

echo "Criar VENV"
pause
python get-pip.py
pip install virtualenv
python -m virtualColdStockWindows
virtualColdStockWindows/Scripts/activate.bat

pip install mysql-connector-python
pip install psutil
pip install requests

echo cd ../../../ >> activate
echo  python ./main.py >> activate

pause
