import cv2

video = cv2.VideoCapture("reflectiveGreen.MOV")

i = 0

while (True):
	ret, orig_frame = video.read()
	if not ret:
		break

	print(i)
	i = i+1

	#hsv = cv2.cvtColor(orig_frame, cv2.COLOR_BGR2HSV)
	#mask0 = cv2.inRange(hsv,(67, 51, 0), (112, 114, 255) )

	cv2.imshow("oof", orig_frame)
	cv2.waitKey(1)
