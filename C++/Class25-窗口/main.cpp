#include <iostream>
#include "Class25/Code01_SlidingWindowMaxArray.cpp"

int main() {
    std::vector<int> a = Class25::Code01_SlidingWindowMaxArray::GetMaxWindow({1,2,3,4,5,6,7,8},3);

    for (int i : a)
        std::cout << i << std::endl;

    std::cout << "Hello, World!" << std::endl;
    return 0;
}