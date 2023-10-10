package baseball;


import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        //난수 생성
        List<Integer> computer = new ArrayList<>();

        /*computer = List.of(7, 1, 3);*/

        String userInput = "";
        String restartInput = "1";

        //사용자가 잘못된 값을 입력할 경우 예외 발생시키고 종료

        int inputTemp = 0;
        String inputStringTemp = "";

        int strikeCnt = 0;
        int ballCnt = 0;

        List<Integer> userInputList = new ArrayList<>();

        System.out.println("숫자 야구 게임을 시작합니다.");

        while (restartInput.equals("1")) {
            computer.clear();
            generateRandomNum(computer);
            playBall(strikeCnt, userInput, userInputList, computer, ballCnt);
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            restartInput = Console.readLine();
        }

        //반복문 동시 추출
        //while next() method 활용

    }

    private static void generateRandomNum(List<Integer> computer) {
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    private static void playBall(int strikeCnt, String userInput, List<Integer> userInputList, List<Integer> computer, int ballCnt) {
        while (strikeCnt != 3) {
            /*try {
            } catch (Exception e) {
                //IllegalArgumentException
            }*/
            //초기화
            for (Integer c : computer) {
                System.out.println("c = " + c);
            }
            strikeCnt = 0;
            ballCnt = 0;
            userInputList.clear();

            System.out.print("숫자를 입력해주세요 : ");
            userInput = Console.readLine();
            //숫자 분리
            separateInputNum(userInput, userInputList);
            /*for (Integer i : userInputList) {
                System.out.println("i = " + i);
            }*/
            //스트라이크 카운트
            strikeCnt = getStrikeCnt(computer, userInputList, strikeCnt);

            //볼카운트
            ballCnt = getBallCnt(computer, userInputList, ballCnt);
            //결과 산출
            /*System.out.println("strikeCnt = " + strikeCnt);
            System.out.println("ballCnt = " + ballCnt);*/
            printGameResult(strikeCnt, ballCnt);
            /*System.out.println("strikeCnt = " + strikeCnt);
            System.out.println("ballCnt = " + ballCnt);*/
            if (strikeCnt == 3) {
                System.out.println(strikeCnt + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
            }
        }
    }

    private static void printGameResult(int strikeCnt, int ballCnt) {
        if(strikeCnt != 0 && ballCnt != 0){
            System.out.println(ballCnt + "볼 " + strikeCnt + "스트라이크");
        } else if (strikeCnt != 0 && ballCnt == 0) {
            System.out.println(strikeCnt + "스트라이크");
        } else if (strikeCnt == 0 && ballCnt != 0) {
            System.out.println(ballCnt + "볼 ");
        } else if (strikeCnt == 0 && ballCnt == 0) System.out.println("낫싱");
    }

    private static int getBallCnt(List<Integer> computer, List<Integer> userInputList, int ballCnt) {
        for (int i = 0; i < 3; i++) {
            if(i == 0) continue;
            if (computer.get(0) == userInputList.get(i)) {
                ballCnt++;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(i == 1) continue;
            if (computer.get(1) == userInputList.get(i)) {
                ballCnt++;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(i == 2) continue;
            if (computer.get(2) == userInputList.get(i)) {
                ballCnt++;
            }
        }
        return ballCnt;
    }

    private static int getStrikeCnt(List<Integer> computer, List<Integer> userInputList, int strikeCnt) {

        for (int i = 0; i < 3; i++) {
            if (computer.get(i) == userInputList.get(i)) {
                strikeCnt++;
            }
        }
        return strikeCnt;
    }


    private static void separateInputNum(String userInput, List<Integer> userInputList) {
        int inputTemp = 0;
        for (int i = 1; i <= 3; i++) {
            inputTemp = Integer.parseInt(userInput.substring(i - 1, i));
            userInputList.add(inputTemp);
        }
    }
}
