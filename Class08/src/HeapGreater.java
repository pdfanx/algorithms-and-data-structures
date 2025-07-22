import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/*
* T一定要是非基础类型
* */
public class HeapGreater <T>{
    private ArrayList<T> heap;
    private int heapSize;
    private HashMap<T,Integer> indexMap;
    private Comparator<? super T> comparator;

    public HeapGreater(Comparator<? super T> comparator)
    {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty()
    {
        return heap.isEmpty();
    }

    public int size()
    {
        return heapSize;
    }

    public T peek()
    {
        return heap.get(0);
    }

    public boolean containsKey(T key)
    {
        return indexMap.containsKey(key);
    }

    public void push(T item)
    {
        heap.add(item);
        indexMap.put(item, heapSize);
        heapInsert(heapSize++);
    }

    public void remove(T item)
    {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(item);
        indexMap.remove(item);
        heap.remove(--heapSize);
        if(item != replace)
        {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public T pop()
    {
        T ans = heap.get(0);
        indexMap.remove(ans);
        swap(0, --heapSize);
        heapify(0);
        heap.remove(heapSize);
        return ans;
    }

    public void clear()
    {
        heap.clear();
        indexMap.clear();
        comparator = null;
        heapSize = 0;
    }

    public void resign(T item)
    {
        heapInsert(indexMap.get(item));
        heapify(indexMap.get(item));
    }

    public List<T> getAllElements()
    {
        List<T> ans = new ArrayList<>();
        for(T item : heap)
        {
            ans.add(item);
        }
        return ans;
    }


    private void heapInsert(int index)
    {
        while(comparator.compare(heap.get(index), heap.get((index - 1)/2)) > 0)
        {
            swap(index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int index)
    {
        int left = (index * 2) + 1;

        while(left < heapSize)
        {
            int largest = left + 1 < heapSize && comparator.compare(heap.get(left), heap.get(left + 1)) < 0
                    ? (left + 1) : left;
            largest = comparator.compare(heap.get(index), heap.get(largest)) > 0 ? index : largest;
            if(index == largest)
            {
                break;
            }
            swap(index, largest);
            index = largest;
            left = (index * 2) + 1;
        }
    }

    private void swap(int index1, int index2)
    {
        T o1 = heap.get(index1);
        T o2 = heap.get(index2);
        heap.set(index1, o2);
        heap.set(index2, o1);
        indexMap.put(o1, index2);
        indexMap.put(o2, index1);
    }
}
