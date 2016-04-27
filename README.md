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
Area: 2.01
Radius: 0.80
Thickness: 0.40
X:    0.000   Y:    0.000
X:    0.089   Y:    0.014
X:    0.179   Y:    0.028
X:    0.271   Y:    0.042
X:    0.367   Y:    0.056
X:    0.466   Y:    0.070
X:    0.570   Y:    0.083
X:    0.680   Y:    0.097
X:    0.796   Y:    0.111
X:    0.918   Y:    0.125
```
Here, the area is the area of the boundary circle of the parabola, which is proportional to the amount of sunlight coming in. Radius is the radius of that circle. Thickness is the depth of the parabola. Then come the measurements.

## License

Copyright © 2016 Jonas Östlund

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
