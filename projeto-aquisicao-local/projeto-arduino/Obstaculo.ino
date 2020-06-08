int objeto = 0;
void setup() 
{
  pinMode(7, INPUT); // porta de leitura do Arduino
  Serial.begin(9600);
}
void loop()
{
  objeto = digitalRead(7); leitura dos dados da porta
  if (objeto == 0)
  {
    Serial.println("1");
  }
  else
  {
    Serial.println("0");
  }
  delay(500);
}
