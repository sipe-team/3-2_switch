# 다솜 - 재룡 목표

다솜 : 재룡에게 Javascript / FE 기초를 알려줄 수 있다
재룡 : 다솜에게 DevOps 및 DevOps의 기본 지식을 알려줄 수 있다.

## 재룡

### Step 1. Docker 개념

- Why we use Docker?
  - Som: 같은 환경에서 빠르게 동일한 실행 결과를 얻고 싶어서. -> 정답 !
- 왜????????????????????????????
  - Keyword : 같은 환경 / 빠르게
- 같은 환경은 오히려 쉬움 ! -> 기존에 VM 이 있었음.
- VM이 왜?
  - Guest OS /<넘사벽> Hypervisor <넘사벽>/ Origin OS
    - 격리된 환경을 만들 수 있습니다.
    - Guest OS "전부" 설치를 해야해요 === 무겁다.
    - 대신? Guest OS 가 감염이 되더라도, Origin OS에는 영향을 미치지 않습니다.
    - 근데 우리가 배포를 하더라도, GuestOS 를 신경쓸까 Origin OS를 신경쓸까? => Guest OS 위에서 서비스 돌아가는데, Guest OS가 죽으면 서비스도 죽어요.
- 그럼? 우리가 이 "넘사벽"을 줄이면 빨라질 수 있지 않을까? => Docker.
- Docker는 어떻게?
  - LXC + Cgroup
    - LXC : 격리된 환경을 만들 수 있습니다. (Like VM)
    - Cgroup: 리소스를 제한할 수 있습니다. (네트워크, 리소스(CPU, Memory))
  - Container / Docker Engine / Origin OS
  - Docker Engine은? Origin의 Kernel을 사용합니다. => Guest OS를 사용하지 않습니다. => Origin의 일부를 그대로 사용해요.
    - e.g. /bin/bash => Origin 도, container도 /bin/bash를 가지고 있다? => Origin의 /bin/bash를 사용합니다.
      - 그럼 뭐가 다른데요?
        - => GuestOS는 /bin/bash 마저도 설치를 함. => 무겁다.
        - => Docker는 /bin/bash를 사용할 뿐, 설치하지 않습니다 공유해요. => 가볍다. (공유하지 못하는 것만 설치를 함.)

## 경랑화 했고, 어떻게 쓰는 지 알았어.

- 겁나 써제꼈음
- 컨테이너가 너ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ무 많아졌으.
- 우리는 이거를 관리해야겠다. => Container Orchestration
  - 수백, 수천개의 컨테이너를 관리한다.
- k8s Google BORG 논문에서 시작을 했고, 현재의 컨테이너 오케스트레이션의 (사실상) 표준 -> docker swarm, _k8s_, mesos
- 쿠베의 "기초" Docker는 어떤 놈이냐?

## Step 2. Docker 구성도

- image
  - Read-only file system
  - Container를 만들기 위한 템플릿
  - Base image라는 "흰 도화지"가 있어요.
  - 그 위에 레이어를 쌓아요.
    - 레이어가 뭐에요?
      - File System이 변경될때마다 레이어가 생겨요.
      - e.g. apt-get update -> 뭐가 바뀔까요? => /var/lib/apt/lists/\* => 레이어가 생겨요.
    - 쌓인 Layer들을 Read-only로 만들어요.
    - Container의 "시작 지점"이 되어요.
    - RE-ZERO 보셨죠? 체크포인트에서 다시 시작합니다.
    - Stateless한 서비스를 만들면? -> 다시 시작하더라도, 똑같은 결과를 얻을 수 있어요.
    - 똑같은 결과가 나오는 친구들을 수평 Scale Out
