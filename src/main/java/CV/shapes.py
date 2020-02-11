import cv2

class ShapeDetector:
	def __init__(self):
		pass

		def detect (self, c):
			#init shape name and approx. contour
			shape = "unidentified"
			peri = cv2.arcLength(c, True)
			approx = cv2.approxPolyDP(c, 0.04 * peri, True)


			if len(approx) == 6:
				shape = "hexagon"

			return shape