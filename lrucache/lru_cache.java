// Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
//
// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
//
// The cache is initialized with a positive capacity.


class LRUCache {
// Dequeue - Maintain the order in which cache blocks are accessed
// Define a Dequeue for brining the most recently used block to the front of the Dequeue
private Deque<Integer> dq;

// Hash Set - Store the keys of the cache block
// Define a Hash Set which would help in keeping track of which blocks are in the cache
private HashSet<Integer> hs;
	static int csize;
	//Hash map - key value pair
  	private HashMap<Integer,Integer> map;

    public LRUCache(int capacity) {
        map= new HashMap<Integer,Integer>();
        dq = new LinkedList<Integer>();
		hs = new HashSet<Integer>();
		csize = capacity;
    }

    public int get(int key) {
        int res = 0;
        if (hs.contains(key)) {
			// return n
			// bring n to from of the queue
			dq.remove(key);
			dq.offerFirst(key);
            res=map.get(key);
        }
        else
            return -1;
        return res;
    }

    public void put(int key, int value) {
        // if queue is full then remove the last item and add n to front
			if (csize == hs.size()) {
                Integer c =null;
                if(hs.contains(key)){
                     dq.remove(key);
                     map.put(key,value);
                     dq.offerFirst(key);
                }
				else{
                   c = dq.removeLast();
                   dq.offerFirst(key);
				   hs.remove(c);
                   map.remove(c);
                   map.put(key,value);
                   hs.add(key);
                }
			} else{
                // add n to queue and add entry in hs
                 if(hs.contains(key)){
                     dq.remove(key);
                     map.put(key,value);
                     dq.offerFirst(key);
                }
				else{
                   dq.offerFirst(key);
                   map.put(key,value);
                   hs.add(key);
                }

            }
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
