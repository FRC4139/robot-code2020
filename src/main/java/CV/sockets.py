import socket

HOST = "localhost"
PORT = 8888
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('Socket created')

try:
    s.bind(('', PORT))
except socket.error as err:
    print('Bind failed. Error Code : ' .format(err))
s.listen(10)
print("Socket Listening")

def receive_message(s):
    conn, addr = s.accept()
    conn.send(bytes("Message"+"\r\n",'UTF-8'))
    print("Message sent")
    data = conn.recv(1024)
    print(data.decode(encoding='UTF-8'))

while True:
    receive_message(s)
