#include <iostream>
#include <vector>
#include <fstream>
#include <cstdlib>
#include <sstream>

using namespace std;

vector<string> stringSplit(string s);

class node {
public:
	bool visited;
	int data;
	int pass1no;
	int leaderNo;
	vector<node*> adjNodes;
	node();
};

class graph {
public:
	vector<node> nodes;
	int number;
	graph();
	graph(string fileName);
	graph reverse();
	void unvisitAll();
	void dfsPass1();
	void dfsPass2();
	void dfs1(node*);
	void dfs2(node*);
	void copyPass1Numbers(graph g);
	void printLeaders();
};

int main() {
	graph p9("sampleListForGraphSCC.txt");
	graph revGraph = p9.reverse();
	revGraph.dfsPass1();
	p9.copyPass1Numbers(revGraph);
	p9.dfsPass2();
	p9.printLeaders();
	return 0;
}

graph::graph() {
	number = -1;
}

void graph::printLeaders() {
	for(int i = 0; i < nodes.size(); i++)
		cout << i << "\t" << nodes[i].leaderNo << "\n";
}

graph::graph(string fileName) {
	number = -1;
	
	ifstream file(&(fileName[0]));
	//file.open();
	vector<string> info;
	string str;
	while(getline(file, str))
	{
		info.push_back(str);
		nodes.push_back(*(new node()));
	}
	file.close();
	for(int i = 0; i < info.size(); i++) {
		vector<string> adjNodeStrings = stringSplit(info[i]);
		for(int j = 0; j < adjNodeStrings.size(); j++) {
			nodes[i].adjNodes.push_back(&(nodes[atoi(&(adjNodeStrings[j][0]))]));
		}
	}
}

vector<string> stringSplit(string s) {
	vector<string> splitted;
	stringstream ss(s);
	string temp;
	while(getline(ss, temp, ' '))
		splitted.push_back(temp);
	return splitted;
}

graph graph::reverse() {
	graph rev;
	for(int i = 0; i < nodes.size(); i++)
		rev.nodes.push_back(*(new node()));
	for(int i = 0; i < nodes.size(); i++)
		for(int j = 0; j < nodes[i].adjNodes.size(); j++)
			rev.nodes[j].adjNodes.push_back(&(rev.nodes[i]));
	return rev;
}

void graph::dfsPass2() {
	unvisitAll();
	for(int i = 0; i < nodes.size(); i++)
		if(!nodes[i].visited) {
			number = nodes[i].pass1no;
			dfs2(&(nodes[i]));
		}
}

void graph::dfs2(node *n) {
	if(n->visited)
		return;
	n->visited = true;
	for(int i = 0; i < n->adjNodes.size(); i++)
		dfs2(n->adjNodes[i]);
	n->leaderNo = number;
}

void graph::copyPass1Numbers(graph g) {
	for(int i = 0; i < nodes.size(); i++)
		nodes[i].pass1no = g.nodes[i].pass1no;
}

void graph::dfsPass1() {
	unvisitAll();
	for(int i = 0; i < nodes.size(); i++)
		if(!nodes[i].visited)
			dfs1(&(nodes[i]));
}

void graph::dfs1(node *n) {
	if(n->visited)
		return;
	n->visited = true;
	for(int i = 0; i < n->adjNodes.size(); i++)
		dfs1(n->adjNodes[i]);
	n->pass1no = ++number;
}

void graph::unvisitAll() {
	for(int i = 0; i < nodes.size(); i++)
		nodes[i].visited = false;
}

node::node() {
	visited = false;
	data = 0;
	pass1no = -1;
	leaderNo = -1;
}
