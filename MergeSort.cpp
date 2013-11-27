#include <vector>
using namespace std;

class MergeSort {
	vector<int> merger(vector<int>, vector<int>);
public:
	vector<int> sort(vector<int>);
};

vector<int> MergeSort::sort(vector<int> v)
{
	int s = v.size();
	if(s == 2)
	{
	    vector<int>v1(1, v[0]), v2(1, v[1]);
		v = merger(v1, v2);
	}
	else if (s == 3)
	{
        vector<int>v1(1, v[0]), v2(v.begin() + 1 , v.end());
		v = merger(v1, sort(v2));
	}
	else
	{
		int x = (s - 1) / 2;
        vector<int>v1(v.begin(), v.begin() + x), v2(v.begin() + x + 1 , v.end());
		v = merger(sort(v1), sort(v2));
	}
	return v;
}

vector<int> MergeSort::merger(vector<int> v1, vector<int> v2)
{
	vector<int> v3(v1.size() + v2.size(), 0);
	int p1 = 0, p2 = 0, p3 = 0;
	while(p3 < v3.size())
	{
		if(p2 == v2.size() || (p1 < v1.size() && v1[p1] < v2[p2]))
		{
			v3[p3] = v1[p1];
			p1++;
		}
		else
		{
			v3[p3] = v2[p2];
			p2++;
		}
		p3++;
	}
	return v3;
}