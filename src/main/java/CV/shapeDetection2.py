import cv2 as cv
import numpy as np

im = cv.imread('frcHexagon.jpg')
imgray = cv.cvtColor(im, cv.COLOR_BGR2GRAY)
ret, thresh = cv.threshold(imgray, 127, 255, 0)
im2, contours, hierarchy = cv.findContours(thresh, cv.RETR_TREE, cv.CHAIN_APPROX_SIMPLE)

cnt = contours[4]
cv.drawContours(img, [cnt], 0, (0,255,0), 3)