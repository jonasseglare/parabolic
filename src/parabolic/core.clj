(ns parabolic.core)


(defn coef-to-focal-point
  "The y coordinate of the focal point of f(x) = k*x^2"
  [k]
  (/ 0.25 k))

(defn sqr [x]
  (* x x))



(defn eval-parabola [k x]
  (* k x x))

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
  {:radius 0.9     ;; The radius of the disk spanned by the edges of the parabola
   :count 20       ;; How many segments to use for discretization
   :thickness 0.2 ;; Controls the bowl-shape of the parabola
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

(defn make-parabolic-segment-sub [args]
  (let [thickness (:thickness args)
        radius (:radius args)
        n (:count args)
        k (/ thickness (sqr radius))
        radial-X (spaced-samples n radius)
        Y (map (parabola-fn k) radial-X)]
    {:radial-X radial-X :Y Y}))

(defn make-parabolic-segment [args0]
  (make-parabolic-segment-sub (merge default-args args0)))
