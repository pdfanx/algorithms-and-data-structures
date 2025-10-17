#include "Code02_AllLessNumSubArray.cpp"
#include <cmath>
#include <random>
#include <iostream>

std::vector<int> generateArray(int maxLen, int maxValue)
{
	std::default_random_engine e;
	std::uniform_int_distribution<int> len(1, maxLen);
	std::uniform_int_distribution<int> value(0, maxValue);
	std::vector<int> res;
	for (int i = 0; i < len(e); i++)
	{
		res.emplace_back(value(e));
	}

	return res;
}


int main()
{
	std::default_random_engine e;
	int maxValue = 20;
	int maxLen = 20;
	std::uniform_int_distribution<int> maxNum(0, maxValue);

	int TestTimes = 30;
	for (int i = 0; i < TestTimes; i++)
	{
		int num = maxNum(e);
		std::vector<int> array = generateArray(maxLen, maxValue);
		int a = Code02_AllLessNumSubArray::Code02_AllLessNumSubArray::right(array, num);
		int b = Code02_AllLessNumSubArray::Code02_AllLessNumSubArray::SubArray(array, num);

		if (a != b)
		{
			std::cout << "OOPs" << std::endl;
			break;
		}

	}

	std::cout << "Finished" << std::endl;
	return 0;
}

