package com.luowei.util;

import java.util.*;

/**
 * map util,user to sort map
 * User: luowei
 * Date: 12-7-8
 * Time: 下午1:56
 */
public abstract class MapUtil {

    /**
     * sort a map by value,note that value must be compareble,
     * in other word,if the value is object instance which must implements the compareble interface
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ){
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int res = o1.getValue().compareTo(o2.getValue());
                return res != 0 ? res : 1; // Special fix to preserve items with equal values
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list){
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

    /**
     * generate a sertedSet which store with map's entry
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public static void main(String[] args){
//        Map<String, Music> nonSortedMap = new HashMap<String, Music>();

//        Music music1 = new Music();
//        music1.setNeid(1);
//        music1.setPeaktime(1l);
//        Music music2 = new Music();
//        music1.setNeid(2);
//        music1.setPeaktime(2l);
//        Music music3 = new Music();
//        music1.setNeid(3);
//        music1.setPeaktime(3l);
//        Music music4 = new Music();
//        music1.setNeid(4);
//        music1.setPeaktime(4l);
//
//        nonSortedMap.put("aaa",music1);
//        nonSortedMap.put("ddd",music2);
//        nonSortedMap.put("bbb",music3);
//        nonSortedMap.put("eee",music4);

//        nonSortedMap.put("aaa", 1);
//        nonSortedMap.put("ddd", 3);
//        nonSortedMap.put("ccc", 1);
//        nonSortedMap.put("rrrr", 2);

//        Map<String,Music> sortedMap = MapUtil.sortByValue(nonSortedMap);
//        for(Map.Entry<String,Music> entry : sortedMap.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
//        for (Map.Entry<String, Integer> entry  : entriesSortedByValues(nonSortedMap)) {
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
    }
}
