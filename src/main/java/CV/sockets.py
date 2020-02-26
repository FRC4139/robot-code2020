import socket
import numpy as np
import cv2

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

def nothing():
    pass

def vision():

    print("starting vision")

    cap = cv2.VideoCapture(0)

    frame_width = 640
    frame_height = 480
    frame_center = 240
    cap.set(3, frame_width)
    cap.set(4, frame_height)


    # Setting up Trackbars
    # cv2.namedWindow('Control Panel')  # makes a control panel
    # cv2.createTrackbar('Hue', 'Control Panel', 112, 180, nothing)  # default 0 205 255 69 8 12
    # cv2.createTrackbar('Sat', 'Control Panel', 114, 255, nothing)
    # cv2.createTrackbar('Val', 'Control Panel', 255, 255, nothing)
    # cv2.createTrackbar('Hrange', 'Control Panel', 67, 127, nothing)
    # cv2.createTrackbar('Srange', 'Control Panel', 51, 127, nothing)
    # cv2.createTrackbar('Vrange', 'Control Panel', 0, 127, nothing)

    # Capture frame-by-frame
    ret, frame = cap.read()

    # Converting frame to hsv
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    # Making the trackbars have actual value
    hue = 112 # cv2.getTrackbarPos('Hue', 'Control Panel')
    sat = 114 # cv2.getTrackbarPos('Sat', 'Control Panel')
    val = 255 # cv2.getTrackbarPos('Val', 'Control Panel')
    hrange = 67 # cv2.getTrackbarPos('Hrange', 'Control Panel')
    srange = 51 # cv2.getTrackbarPos('Srange', 'Control Panel')
    vrange = 0 # cv2.getTrackbarPos('Vrange', 'Control Panel')

    # Settting the pixels that have a certain value *cough*discrimination*cough*
    colorLower = (hue - hrange, sat - srange, val - vrange)
    colorUpper = (hue + hrange, sat + srange, val + vrange)

    # masking out the right pixels and finding countours
    mask = cv2.inRange(hsv, colorLower, colorUpper)
    cnts = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)[-2]

    final = "error"
    # drawing the biggest contour if a group of pixels is detected
    if len(cnts) > 0:
        # c is the biggest contour array in the frame
        c = max(cnts, key=cv2.contourArea)

        # calculating the radius and center of the biggest contour
        ((x, y), radius) = cv2.minEnclosingCircle(c)
        center_x = (x+radius)/2
        center_y = (y+radius)/2
        threshold = 0.1*frame_width
        cv2.circle(frame, (int(x), int(y)), int(radius), (255, 255, 255), 5, 2)
        # cv2.circle(mask, (int(x), int(y)), int(radius), color[, thickness[, lineType[, shift]]])
        if(center_x > (frame_center+threshold)):
            final = "left"
            print("left")
        if(center_x < (frame_center-threshold)):
            final = "right"
            print("right")
        if(center_x > (frame_center-threshold) and center_x < (frame_center+threshold)):
            final = "center"
            print("center")

    #displaying what the program is seeing
    # cv2.imshow("Frame", frame)
    # cv2.imshow("mask", mask)

    #Giving the masked pixels color
    result = cv2.bitwise_and(frame, frame, mask=mask)
    # cv2.imshow("Result", result)

    # When everything done, release the capture
    cap.release()
    # cv2.destroyAllWindows()

    return final


def receive_message(s):
    print("accepting")
    conn, addr = s.accept()
    '''
    conn.send(bytes("Message"+"\r\n",'UTF-8'))
    print("Message sent")
    '''

    print("receiving")
    data = conn.recv(1024)
    print("decoding and printing")
    print(data.decode(encoding='UTF-8'))

    string = data.decode(encoding='UTF-8')
    if string == "vision_request\r\n":
        result = vision()
        conn.send(bytes(result+"\r\n", 'UTF-8'))
    else:
        conn.send(bytes("invalid request\r\n", 'UTF-8'))

try:
    while True:
        receive_message(s)
except KeyboardInterrupt:
    print("Interrupted!")
    exit(0)
