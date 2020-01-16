#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <queue>
using namespace std;

#define endl '\n'
#define MAX 125 + 1

int N;
int ans = 987654321;
int map[MAX][MAX];
int d[MAX][MAX];

int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };

void BFS(int sx, int sy) {
	queue<pair<int, int> > q;
	q.push({ sx,sy });
	d[sx][sy] = map[sx][sy];
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k]; int ny = y + dy[k];
			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (d[nx][ny] > d[x][y] + map[nx][ny]) {
					d[nx][ny] = d[x][y] + map[nx][ny];
					if (nx == N - 1 && ny == N - 1) {
						if (ans > d[nx][ny]) {
							ans = d[nx][ny];
						}
					}
					else {
						q.push({ nx,ny });
					}
				}
			}
		}
	}
}

int main() {
	//	freopen("input.txt", "r", stdin);
	int T = 1;
	while (true) {
		cin >> N;

		if (N == 0) break;
		ans = 987654321;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map[i][j];
				d[i][j] = 987654321;
			}
		}
		BFS(0, 0);

		cout << "Problem " << T << ": " << ans << endl;
		T++;
	}
	return 0;
}