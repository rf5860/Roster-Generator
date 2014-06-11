(ns roster-constructor.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]))

(def ^:const MILLIS_IN_SECOND 1000)
(def ^:const MILLIS_IN_MINUTE 60000)
(def ^:const MILLIS_IN_HOUR 3600000)
(def ^:const MILLIS_IN_DAY 86400000)

(defrecord Roster [rosterUuid rosterDuration])
(defrecord RosterItem  [rosterUuid itemStart itemFinish])
(defrecord RosterAllocation [uuid allocationType allocationId rosterStart rosterFinish rosterUuid])
(defrecord RosterExclusion [uuid allocationType allocationId startFinish])

(def roster1 (Roster. 1 (* 7 MILLIS_IN_DAY)))

(def roster1_items [(RosterItem. 1 ; Monday
                                 0
                                 (* MILLIS_IN_HOUR 8))
                    (RosterItem. 1 ; Monday
                                 (* MILLIS_IN_HOUR 17)
                                 (+
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 1 ; Tuesday
                                 (+
                                  (* MILLIS_IN_DAY 1))
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 1 ; Tuesday
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 1 ; Wednesday
                                 (+
                                  (* MILLIS_IN_DAY 2))
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 1 ; Wednesday
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 1 ; Thursday
                                 (+
                                  (* MILLIS_IN_DAY 3))
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 1 ; Thursday
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 1 ; Friday
                                 (+
                                  (* MILLIS_IN_DAY 4))
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 1 ; Friday
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 1 ; Saturdy  / Sunday
                                 (+
                                  (* MILLIS_IN_DAY 5))
                                 (+
                                  (* MILLIS_IN_DAY 6)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))])

(def roster2 (Roster. 2 (* 7 MILLIS_IN_DAY)))

(def roster2_items [(RosterItem. 2 0 (* MILLIS_IN_HOUR 22))
                    (RosterItem. 2
                                 (+ (* MILLIS_IN_DAY 1)
                                    (* MILLIS_IN_HOUR 6)) 
                                 (+ (* MILLIS_IN_DAY 1)
                                    (* MILLIS_IN_HOUR 22)))
                    (RosterItem. 2
                                 (+ (* MILLIS_IN_DAY 2)
                                    (* MILLIS_IN_HOUR 6)) 
                                 (+ (* MILLIS_IN_DAY 2)
                                    (* MILLIS_IN_HOUR 22)))
                    (RosterItem. 2
                                 (+ (* MILLIS_IN_DAY 3)
                                    (* MILLIS_IN_HOUR 6)) 
                                 (+ (* MILLIS_IN_DAY 3)
                                    (* MILLIS_IN_HOUR 22)))
                    (RosterItem. 2
                                 (+ (* MILLIS_IN_DAY 4)
                                    (* MILLIS_IN_HOUR 6)) 
                                 (+ (* MILLIS_IN_DAY 4)
                                    (* MILLIS_IN_HOUR 22)))
                    (RosterItem. 2
                                 (+ (* MILLIS_IN_DAY 5)
                                    (* MILLIS_IN_HOUR 6))
                                 (+
                                  (* MILLIS_IN_DAY 6)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))])

(def roster3 (Roster. 3 (* 9 MILLIS_IN_DAY)))

(def roster3_items [(RosterItem. 3 
                                 0
                                 (* MILLIS_IN_HOUR 7))
                    (RosterItem. 3 
                                 (* MILLIS_IN_HOUR 16)
                                 (+ (* MILLIS_IN_DAY 1)
                                    (* MILLIS_IN_HOUR 7)))
                    (RosterItem. 3 
                                 (+ (* MILLIS_IN_DAY 1)
                                    (* MILLIS_IN_HOUR 16))
                                 (+ (* MILLIS_IN_DAY 2)
                                    (* MILLIS_IN_HOUR 7)))
                    (RosterItem. 3 
                                 (+ (* MILLIS_IN_DAY 2)
                                    (* MILLIS_IN_HOUR 16))
                                 (+ (* MILLIS_IN_DAY 3)
                                    (* MILLIS_IN_HOUR 7)))
                    (RosterItem. 3 
                                 (+ (* MILLIS_IN_DAY 3)
                                    (* MILLIS_IN_HOUR 16))
                                 (+ (* MILLIS_IN_DAY 4)
                                    (* MILLIS_IN_HOUR 7)))
                    (RosterItem. 3 
                                 (+ (* MILLIS_IN_DAY 4)
                                    (* MILLIS_IN_HOUR 16))
                                 (+ (* MILLIS_IN_DAY 5)
                                    (* MILLIS_IN_HOUR 7)))
                    (RosterItem. 3 
                                 (+ (* MILLIS_IN_DAY 5)
                                    (* MILLIS_IN_HOUR 16))
                                 (+ (* MILLIS_IN_DAY 6)
                                    (* MILLIS_IN_HOUR 7)))
                    (RosterItem. 3
                                 (+ (* MILLIS_IN_DAY 6)
                                    (* MILLIS_IN_HOUR 16))
                                 (+
                                  (* MILLIS_IN_DAY 8)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))])

(def roster4 (Roster. 4 (* 7 MILLIS_IN_DAY)))

(def roster4_items [(RosterItem. 4 ; Monday
                                 0
                                 (* MILLIS_IN_HOUR 8))
                    (RosterItem. 4 ; Monday
                                 (* MILLIS_IN_HOUR 17)
                                 (+
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 4 ; Tuesday
                                 (+
                                  (* MILLIS_IN_DAY 1))
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 4 ; Tuesday
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 4 ; Wednesday
                                 (+
                                  (* MILLIS_IN_DAY 2))
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 4 ; Wednesday
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 4 ; Thursday
                                 (+
                                  (* MILLIS_IN_DAY 3))
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 4 ; Thursday
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 4 ; Friday
                                 (+
                                  (* MILLIS_IN_DAY 4))
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 4 ; Friday
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 4 ; Saturdy  / Sunday
                                 (+
                                  (* MILLIS_IN_DAY 5))
                                 (+
                                  (* MILLIS_IN_DAY 6)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))])

(def roster5 (Roster. 5 (* 7 MILLIS_IN_DAY)))

(def roster5_items [(RosterItem. 5 0 (* MILLIS_IN_HOUR 22))
                    (RosterItem. 5
                                 (+ (* MILLIS_IN_DAY 1)
                                    (* MILLIS_IN_HOUR 6)) 
                                 (+ (* MILLIS_IN_DAY 1)
                                    (* MILLIS_IN_HOUR 22)))
                    (RosterItem. 5
                                 (+ (* MILLIS_IN_DAY 2)
                                    (* MILLIS_IN_HOUR 6)) 
                                 (+ (* MILLIS_IN_DAY 2)
                                    (* MILLIS_IN_HOUR 22)))
                    (RosterItem. 5
                                 (+ (* MILLIS_IN_DAY 3)
                                    (* MILLIS_IN_HOUR 6)) 
                                 (+ (* MILLIS_IN_DAY 3)
                                    (* MILLIS_IN_HOUR 22)))
                    (RosterItem. 5
                                 (+ (* MILLIS_IN_DAY 4)
                                    (* MILLIS_IN_HOUR 6)) 
                                 (+ (* MILLIS_IN_DAY 4)
                                    (* MILLIS_IN_HOUR 22)))
                    (RosterItem. 5
                                 (+ (* MILLIS_IN_DAY 5)
                                    (* MILLIS_IN_HOUR 6))
                                 (+
                                  (* MILLIS_IN_DAY 6)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))])

(def roster6 (Roster. 6 (* 7 MILLIS_IN_DAY)))

(def roster6_items [(RosterItem. 6 ; Monday
                                 0
                                 (* MILLIS_IN_HOUR 8))
                    (RosterItem. 6 ; Monday
                                 (* MILLIS_IN_HOUR 17)
                                 (+
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 6 ; Tuesday
                                 (+
                                  (* MILLIS_IN_DAY 1))
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 6 ; Tuesday
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 1)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 6 ; Wednesday
                                 (+
                                  (* MILLIS_IN_DAY 2))
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 6 ; Wednesday
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 2)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 6 ; Thursday
                                 (+
                                  (* MILLIS_IN_DAY 3))
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 6 ; Thursday
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 3)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 6 ; Friday
                                 (+
                                  (* MILLIS_IN_DAY 4))
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 8)))
                    (RosterItem. 6 ; Friday
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 17))
                                 (+
                                  (* MILLIS_IN_DAY 4)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))
                    (RosterItem. 6 ; Saturdy  / Sunday
                                 (+
                                  (* MILLIS_IN_DAY 5))
                                 (+
                                  (* MILLIS_IN_DAY 6)
                                  (* MILLIS_IN_HOUR 23)
                                  (* MILLIS_IN_MINUTE 59)
                                  (* MILLIS_IN_SECOND 59)))])

; Rosters
(def rosters [roster1 roster2 roster3 roster4 roster5 roster6])
; Roster Items
(def roster-items [roster1_items roster2_items roster3_items roster4_items roster5_items roster6_items])

(def date-format (f/formatter "MM/dd/yyyy"))
(defn to-oracle-date
  "Convert a string into a TO_DATE for Oracle Insert"
  [date]
  (str "TO_DATE('" date "', 'mm/dd/yyyy')"))
; Entities
(def employee1 "SC4660")
(def employee2 "PAULINE")
(def employee3 "MYLESH")
(def employee4 "CARMELD")
(def wg1 "WEM")
(def wg2 "MWORK")
(def wg3 "FWORK")
(def crew1 "CREW A")
(def crew2 "PINK")

                                        ; Employees Allocations
(def employee1_allocations [(RosterAllocation. 1 "emp" employee1 (to-oracle-date (f/unparse date-format (t/date-time 2014 3 31))) nil 1)])
(def employee2_allocations [(RosterAllocation. 2 "emp" employee2 (to-oracle-date (f/unparse date-format (t/date-time 2014 3 31))) nil 4)])
(def employee3_allocations [(RosterAllocation. 3 "emp" employee3 (to-oracle-date (f/unparse date-format (t/date-time 2014 3 31))) nil 6)])
(def employee4_allocations [(RosterAllocation. 4 "emp" employee4 (to-oracle-date (f/unparse date-format (t/date-time 2014 5 1))) (to-oracle-date (f/unparse date-format (t/date-time 2014 5 31 23 59 59))) 1)
                            (RosterAllocation. 5 "emp" employee4 (to-oracle-date (f/unparse date-format (t/date-time 2014 9 6))) (to-oracle-date (f/unparse date-format (t/date-time 2014 6 30 23 59 59))) 5)])
                                        ; Work Group Allocations
(def workGroup1_allocations [(RosterAllocation. 6 "wg" wg1 (to-oracle-date (f/unparse date-format (t/date-time 2014 3 31))) nil 2)])
(def workGroup2_allocations [(RosterAllocation. 7 "wg" wg2 (to-oracle-date (f/unparse date-format (t/date-time 2014 3 31))) nil 3)])
(def workGroup3_allocations [(RosterAllocation. 8 "wg" wg3 (to-oracle-date (f/unparse date-format (t/date-time 2014 5 1))) (to-oracle-date (f/unparse date-format (t/date-time 2014 5 28 23 59 59))) 4)
                             (RosterAllocation. 9 "wg" wg3 (to-oracle-date (f/unparse date-format (t/date-time 2014 5 29))) (to-oracle-date (f/unparse date-format (t/date-time 2014 6 30 23 59 59))) 2)])
                                        ; Crews Allocations
(def crew1_allocations [(RosterAllocation. 10 "crew" crew1 (to-oracle-date (f/unparse date-format (t/date-time 2014 3 31))) nil 2)])
(def crew2_allocations [(RosterAllocation. 11 "crew" crew2 (to-oracle-date (f/unparse date-format (t/date-time 2014 3 31))) nil 5)])

(def roster-allocations [crew1_allocations crew2_allocations employee1_allocations employee2_allocations employee3_allocations employee4_allocations
                         workGroup1_allocations workGroup2_allocations workGroup3_allocations])

(defn static? [field]
  (java.lang.reflect.Modifier/isStatic
   (.getModifiers field)))

(defn get-values
  "Generate a list of values for the given entity"
  [entity]
  (clojure.string/upper-case
   (clojure.string/join "," (map #(let [base-value (% entity)
                                        value (.toUpperCase (.toString (if (nil? base-value) "" base-value)))
                                        key (.toUpperCase (name %))]
                                    (if (and (not (nil? base-value)) (or (.contains key "START") (.contains key "FINISH")))
                                      value
                                      (str "'" value "'")))
                                 (keys entity)))))

(defn insertUndrscore
  [s]
  (clojure.string/replace s #"[A-Z]" #(str "_" (clojure.string/lower-case %1))))

(defn get-columns
  [entity]
  (clojure.string/upper-case
   (clojure.string/join "," (map insertUndrscore
                                 (map #(name %) (keys entity))))))

(defn generate-sql
  "Generate sql from the given entitiy"
  [entity table]
  (str "INSERT INTO " table " (" (apply str (get-columns entity)) ") VALUES (" (apply str (get-values entity))  ");"))

(defn generate-multiple-sql
  "Generate multiple pieces of SQL"
  [entitites table]
  (map #(generate-sql % table) entitites))

(defn generate-rosters
  "Generate the Rosters"
  []
  (map #(generate-sql % "WP_ROSTER") rosters))

(defn generate-allocations
  "Generate the Roster Allocations"
  []
  (map #(generate-multiple-sql % "WP_ROSTER_ALLOCATION") roster-allocations))

(defn generate-roster-items
  "Generate the Roster Items"
  []
  (map #(generate-multiple-sql % "WP_ROSTER_ITEM") roster-items))

(spit "sql.sql" (clojure.string/join "\n" (flatten `(~(generate-roster-items) ~(generate-rosters) ~(generate-allocations)))))
