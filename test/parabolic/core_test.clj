(ns parabolic.core-test
  (:require [clojure.test :refer :all]
            [parabolic.core :refer :all]))

(deftest parabola-tests
  (testing "Parabola"
    (is (= (coef-to-focal-point 1) 0.25))
    (is (= (eval-parabola 1.0 0) 0.))
    (is (= (eval-parabola 1.0 -1) 1.0))
    (is (= (eval-parabola 1.0 1) 1.0))
    (is (= (eval-parabola 1.0 2) 4.0))
    (is (= (eval-parabola 1.5 2) 6.0))
    (is (= (inv-eval-parabola 1.5 6.0) 2.0))
    (is (= [0 1 2 3] (compute-cumulative-sum [1 1 1])))
    (is (= [0.0 1.0 2.0 3.0] (spaced-samples 4 3)))
    (is (= [1.0 1.0] (line-lengths [0 0 0] [1 2 3])))
    (is (= [5.0] (line-lengths [0 3] [0 4])))))
