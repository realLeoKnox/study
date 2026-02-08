#include <stdio.h>
/*已知Mike成绩是98，John成绩是89，可在登记成绩时因为看错行，导致Mike的成绩被错误登记在John那里，现在要把两位的成绩纠正过来。
    1. 创建两个整形变量mike_score和john_score,分别初始化为89和98.
    2. 将John的成绩赋值给Mike
    3. 将John的成绩更改为89
    4. 打印出两个人的成绩
*/
int main(){
    int mike_score=89,john_score=98;
    printf("当前Mike的成绩是%d,John的成绩是%d\n",mike_score,john_score);
    int centra;
    centra=mike_score;
    mike_score=john_score;
    john_score=centra;
    printf("更正后Mike的成绩是%d,John的成绩是%d\n",mike_score,john_score);

    return 0;
}