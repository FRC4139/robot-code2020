import numpy as np
import cv2


def nothing(x):
    pass

cap = cv2.VideoCapture(0)

frame_width = 640
frame_height = 480
frame_center = 240
cap.set(3, frame_width)
cap.set(4, frame_height)


# Setting up Trackbars
cv2.namedWindow('Control Panel')  # makes a control panel
cv2.createTrackbar('Hue', 'Control Panel', 112, 180, nothing)  # default 0 205 255 69 8 12
cv2.createTrackbar('Sat', 'Control Panel', 55, 255, nothing)
cv2.createTrackbar('Val', 'Control Panel', 255, 255, nothing)
cv2.createTrackbar('Hrange', 'Control Panel', 26, 127, nothing)
cv2.createTrackbar('Srange', 'Control Panel', 57, 127, nothing)
cv2.createTrackbar('Vrange', 'Control Panel', 0, 127, nothing)

while (True):
    # Capture frame-by-frame
    ret, frame = cap.read()

    # Converting frame to hsv
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    # Making the trackbars have actual value
    hue = cv2.getTrackbarPos('Hue', 'Control Panel')
    sat = cv2.getTrackbarPos('Sat', 'Control Panel')
    val = cv2.getTrackbarPos('Val', 'Control Panel')
    hrange = cv2.getTrackbarPos('Hrange', 'Control Panel')
    srange = cv2.getTrackbarPos('Srange', 'Control Panel')
    vrange = cv2.getTrackbarPos('Vrange', 'Control Panel')

    # Settting the pixels that have a certain value *cough*discrimination*cough*
    colorLower = (hue - hrange, sat - srange, val - vrange)
    colorUpper = (hue + hrange, sat + srange, val + vrange)

    # masking out the right pixels and finding countours
    mask = cv2.inRange(hsv, colorLower, colorUpper)
    cnts = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)[-2]

    # drawing the biggest contour if a group of pixels is detected
    if len(cnts) > 0:
        # c is the biggest contour array in the frame
        c = max(cnts, key=cv2.contourArea)

        # calculating the radius and center of the biggest contour
        ((x, y), radius) = cv2.minEnclosingCircle(c)
        center_x = (x+radius)/2
        center_y = (y+radius)/2
        threshold = 0.2*frame_width
        cv2.circle(frame, (int(x), int(y)), int(radius), (255, 255, 255), 5, 2)
        # cv2.circle(mask, (int(x), int(y)), int(radius), color[, thickness[, lineType[, shift]]])
        if(center_x > (frame_center+threshold-40)):
            print("left")
        if(center_x < (frame_center-threshold-40)):
            print("right")
        if(center_x > (frame_center-threshold-40) and center_x < (frame_center+threshold-40)):
            print("center")

    #displaying what the program is seeing
    cv2.imshow("Frame", frame)
    cv2.imshow("mask", mask)

    #Giving the masked pixels color
    result = cv2.bitwise_and(frame, frame, mask=mask)
    cv2.imshow("Result", result)

    # Display the resulting frame
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# When everything done, release the capture
cap.release()
cv2.destroyAllWindows()
