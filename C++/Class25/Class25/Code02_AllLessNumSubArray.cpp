// 给定一个整型数组arr，和一个整数num
// 某个arr中的子数组sub，如果想达标，必须满足：
// sub中最大值 – sub中最小值 <= num，
// 返回arr中达标子数组的数量
#include <vector>
#include <deque>	// 双端队列

namespace Code02_AllLessNumSubArray
{
	// 窗口问题：两个窗口 -- 最值问题，一个求最大值，一个求最小值
	class Code02_AllLessNumSubArray
	{
	public:
		static int right(std::vector<int> arr, int num)
		{
			if (arr.size() <= 1)
				return 0;

			int L = arr.size();
			int count = 0;
			for (int i = 0; i < L; i++)
			{
				int Max = arr[i];
				int Min = arr[i];
				for (int j = i + 1; j < L; j++)
				{
					Max = arr[j] > Max ? arr[j] : Max;
					Min = arr[j] < Min ? arr[j] : Min;

					if (Max - Min <= num)
					{
						count++;
					}
					else
					{
						break;
					}
				}
  
			}

			return count;
		}

		static int SubArray(std::vector<int> arr,int num)
		{
			if (arr.size() <= 1)
				return 0;

			std::deque<int> max = {arr[0]};
			std::deque<int> min = {arr[0]};
			int ans = 0;
			int i = 0, j = 0;

			while (i < arr.size() && i >= j)
			{
				while (!max.empty() && arr[i] > max.back())
				{
					max.pop_back();
				}
				max.push_back(arr[i]);

				while (!min.empty() && arr[i] < min.back())
				{
					min.pop_back();
				}
				min.push_back(arr[i]);

				if (max.front() - min.front() <= num)
				{
					i++;
				}
				else
				{
					ans += i - j - 1;

					if (arr[j] == max.front())
					{
						max.pop_front();
					}

					if (arr[j] == min.front())
					{
						min.pop_front();
					}

					j++;
				}
			}
				


			return ans;
		}
	};
}