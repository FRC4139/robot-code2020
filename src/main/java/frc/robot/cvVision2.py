

import cv2
import numpy as np
import math
import time
from nms import non_max_suppression_fast

i = 0

# construct the argument parser and parse the arguments
video = cv2.videoCapture(1)

# Downscale the image to a reasonable size to reduce compute
scale = .5


# Minimize false detects by eliminating contours less than a percentage of the image
area_threshold = 0.01
croppedPixels = 150

ret, orig_frame = video.read()
width = orig_frame.shape[0]
height = orig_frame.shape[1] - croppedPixels
dim = (int(scale*height), int(scale*width))

while (True):

	ret, orig_frame = video.read()
	if not ret:
		break
	print(i)
	i = i+1

	#cropped = orig_frame[croppedPixels:, :]
	#cv2.imshow("cropped", cropped)
 
	orig_frame = cv2.resize(orig_frame, dim, interpolation = cv2.INTER_AREA)
	frame = cv2.GaussianBlur(orig_frame, (5, 5), 0)
	hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
	
	mask = cv2.inRange(hsv,(75, 10, 10), (165, 255, 255) )	

	ret, thresh = cv2.threshold(mask, 127, 255,0)
    #Erosions and dilations
    #erosions are apploed to reduce the size of foreground objects
	kernel = np.ones((3,3),np.uint8)
	eroded = cv2.erode(thresh, kernel, iterations=0)	
	dilated = cv2.dilate(eroded, kernel, iterations=3) 
	#cv2.imshow("dilated", dilated)
	#cv2.waitKey(0)
	#cv2.imshow("Edged", edged)

	cnts,hierarchy = cv2.findContours(dilated,cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
	#cv2.drawContours(orig_frame, cnts, -1, (0, 255, 0), 3)
	cnts = sorted(cnts, key = cv2.contourArea, reverse = True)[:60]

	boundingBoxes = np.empty((0, 4), float)
	if len(cnts) > 0:

		M = cv2.moments(cnts[0])
		for c in cnts:
			rect = cv2.minAreaRect(c)
			#print("rect: {}".format(rect))

			# the order of the box points: bottom left, top left, top right,
			# bottom right
			box = cv2.boxPoints(rect)
			box = np.int0(box)

			#print("bounding box: {}".format(box))
			cv2.drawContours(orig_frame, [box], 0, (0, 0, 255), 2)
			#x,y,w,h = cv2.boundingRect(c)

			#boundingBoxes = np.append(boundingBoxes, np.array([[x,y,x+w,y+h]]), axis = 0)
			#cv2.rectangle(orig_frame,(x,y), (x+w, y+h), (255,0,0), 2)
			cv2.imshow("bounding rectangle",orig_frame)

			#print(str(x/width) + " " + str(y/height) + " " + str((x+w)/width) + " " +  str((y+h)/height))

			box0 = (box[0])
			print(box0[0]/width)
			print(box0[1]/height)

			box1 = (box[1])
			print(box1[0]/width)
			print(box0[1]/height)

			box2 = (box[2])
			print(box2[0]/width)
			print(box2[1]/height)

			box3 = (box[3])
			print(box3[0]/width)
			print(box3[1]/height)
				
			time.sleep(.01)
		
	# perform non-maximum suppression on the bounding boxes
	pick = non_max_suppression_fast(boundingBoxes, 0.1)
	print ("[x] after applying non-maximum, %d bounding boxes" % (len(pick)))

	# loop over the picked bounding boxes and draw them
	for (startX, startY, endX, endY) in pick:
		cv2.rectangle(frame, (startX, startY), (endX, endY), (255, 255, 255),

	# display the images
	#cv2.imshow("Original", orig)
	cv2.imshow("After NMS", frame)
	#cv2.waitKey(0)

	if cv2.waitKey(1) & 0xFF == ord(q):
		break

video.release()
cv2.destroyAllWindows()

