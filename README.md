# parabolic

A Clojure library for making parabolas for solar cooking using cardboard segments. This code
calculates the shapes of those segments.

## Usage

Call 
```
(disp-segment (make-parabolic-segment {}))
```
to generate a table of measurements, such as
```
Number of segments: 16
Area: 2.01
Radius: 0.80
Thickness: 0.40
X:    0.000   Y:    0.000
X:    0.089   Y:    0.017
X:    0.179   Y:    0.035
X:    0.271   Y:    0.052
X:    0.367   Y:    0.069
X:    0.466   Y:    0.087
X:    0.570   Y:    0.104
X:    0.680   Y:    0.121
X:    0.796   Y:    0.139
X:    0.918   Y:    0.156
```
Here, the area is the area of the boundary circle of the parabola, which is proportional to the amount of sunlight coming in. Radius is the radius of that circle. Thickness is the depth of the parabola. Then come the measurements.

## License

Copyright © 2016 Jonas Östlund

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
