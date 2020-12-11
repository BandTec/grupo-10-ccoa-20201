echo "Criando ambiente"
python get-pip.py
pip install virtualenv
python -m venv virtualColdStockWindows
echo "ambiente criado :)"
call .\virtualColdStockWindows\Scripts\activate.bat
echo "instalando pacotes"
pip install mysql-connector-python
pip install psutil
pip install requests

echo "Adicionando texto"

echo python ../../main.py >> virtualColdStockWindows/Scripts/activate.bat


pause