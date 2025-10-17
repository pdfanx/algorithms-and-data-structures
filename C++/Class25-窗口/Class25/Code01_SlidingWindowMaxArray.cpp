#include <vector>
//
// Created by Administrator on 2025/10/16.
//
namespace Class25 {
    template<class T>
    class LinkedList {
    private:
        std::vector<T> data;
    public:
        LinkedList() {
            data = std::vector<T>(10);
        }

        bool isEmpty() {
            return data.empty();
        }

        void PushBack(T item) {
            data.push_back(item);
        }

        void PopBack() {
            data.pop_back();
        }

        T& peekback() {
            return data[data.size() - 1];
        }


        T& front() {
            return data.front();
        }

        T& operator[] (int index) {
            return data[index];
        }
    };


    class Code01_SlidingWindowMaxArray {
    public:
        static std::vector<int> GetMaxWindow(std::pmr::vector<int> arr,int w) {
            if (arr.size() == 0 || w < 1 || arr.size() < w) {
                return {};
            }
            LinkedList<int> qmax = LinkedList<int>();

            int index = 0;
            std::vector<int> ans;
            for (int R = 0; R < arr.size(); R++) {
                while (!qmax.isEmpty()&&arr[qmax.peekback()] <= arr[R]) {
                    qmax.PopBack();
                }
                qmax.PushBack(R);
                if (qmax.front() == R - w)
                    qmax.PopBack();
                if (R >= w - 1) {
                    ans[index++] =arr[qmax.front()];
                }
            }
            return ans;
        }
    };
}