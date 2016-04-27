(ns parabolic.core)


(defn coef-to-focal-point
  "The y coordinate of the focal point of f(x) = k*x^2"
  [k]
  (/ 0.25 k))

(defn sqr [x]
  (* x x))


(defn eval-parabola [k x]
  (* k x x))

(defn radius-to-thickness [radius]
  (eval-parabola (/ 0.5 radius) radius))

(defn parabola-fn [k]
  (fn [x]
    (eval-parabola k x)))

(defn inv-eval-parabola [k y]
  (Math/sqrt (/ y k)))

(defn accumulate-sum [sum-vec x]
  (assert (not (empty? sum-vec)))
  (conj sum-vec (+ (last sum-vec) x)))

(defn compute-cumulative-sum [x]
  (reduce accumulate-sum [0] x))

(def default-args
  {:radius 0.8     ;; The radius of the disk spanned by the edges of the parabola
   :count 20       ;; How many segments to use for discretization
   :samples 8})    ;; How many measurement points make up each segment

(defn spaced-samples [n maxv]
  (let [f (/ (double maxv) (- n 1))]
    (map (fn [y] (* f y)) (range n))))

(defn line-lengths [X Y]
  (let [pairs (map (fn [x y] [x y]) X Y)]
    (map 
     (fn [[x0 y0] [x1 y1]]
       (Math/sqrt (+ (sqr (- x0 x1))
                     (sqr (- y0 y1)))))
     (rest pairs)
     (butlast pairs))))

(defn compute-half-widths [args]
  (let [angle (/ Math/PI (:count args))
        f (Math/sin angle)]
    (spaced-samples (:samples args) (* f (:radius args)))))
    


(defn make-parabolic-segment-sub [args]
  (let [thickness (:thickness args)
        radius (:radius args)
        n (:count args)
        k (/ thickness (sqr radius))
        radial-X (spaced-samples n radius)
        Y (map (parabola-fn k) radial-X)
        segment-X (compute-cumulative-sum (line-lengths radial-X Y))]
    {:radial-X radial-X 
     :Y Y 
     :segment-X segment-X
     :area (* Math/PI (sqr radius))}))

(defn apply-thickness [args]
  (if (contains? args :thickness)
    args
    (assoc args :thickness (radius-to-thickness (:radius args)))))

(defn make-parabolic-segment [args0]
  (make-parabolic-segment-sub 
   (apply-thickness (merge default-args args0))))
