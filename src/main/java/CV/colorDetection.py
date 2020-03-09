import cv2
import numpy as np
import math
import time

i = 0

video = cv2.VideoCapture("reflectiveGreen.MOV")

# Downscale the image to a reasonable size to reduce compute
#scale = .5

# Minimize false detects by eliminating contours less than a percentage of the image
#area_threshold = 0.01
#croppedPixels = 150

#ret, orig_frame = video.read()
#width = orig_frame.shape[0]
#height = orig_frame.shape[1] - croppedPixels
#dim = (int(scale*height), int(scale*width))

while (True):

	ret, orig_frame = video.read()
	if not ret:
		break
	print(i)
	i = i+1

	#ropped = orig_frame[croppedPixels:, :]
	#cv2.imshow("cropped", cropped)
 
	#orig_frame = cv2.resize(orig_frame, dim, interpolation = cv2.INTER_AREA)
	#frame = cv2.GaussianBlur(orig_frame, (5, 5), 0)
	hsv = cv2.cvtColor(orig_frame, cv2.COLOR_BGR2HSV)
	
	mask0 = cv2.inRange(hsv,(67, 51, 0), (112, 114, 255) )

	cv2.imshow("big brain", mask0)
	
