//전역변수로 counter 0
// 10 ** 3

let counter = 0;
// function callLocal(n, cb) {
//   const add = 10 ** n;
//     counter += add;
//     console.log(`[local] 10^${n} = ${add} -> counter : ${counter}`)
//     if(cb) cb();
// }
//
// function callAsync(n, cb) {
//   const add = 10 ** n;
//   const delay = Math.floor(Math.random() * 1000);
//   setTimeout(() => {
//     counter += add;
//     console.log(`[async] 10^${n} = ${add} -> counter : ${counter}, delay : ${delay}ms`)
//     if(cb) cb();
//   }, delay);
// }

function callLocal(n) {
  return new Promise((resolve) => {
    const add = 10 ** n;
    counter += add;
    console.log(`[local] 10^${n} = ${add} -> counter : ${counter}`)
    resolve();
  });
}

function callAsync(n) {
  return new Promise((resolve) => {
    const add = 10 ** n;
    const delay = Math.floor(Math.random() * 1000);
    setTimeout(() => {
      counter += add;
      console.log(`[async] 10^${n} = ${add} -> counter : ${counter}, delay : ${delay}ms`)
      resolve();
    }, delay);

  });
}
// callAsync(0)
//     .then(() => callAsync(1))
//     .then(() => callAsync(2))
//     .then(() => callAsync(3))
//     .then(() => callAsync(4))
//     .then(() => callLocal(5))
//     .then(() => callLocal(6))
//     .then(() => callLocal(7))
//     .then(() => callLocal(8))
//     .then(() => callLocal(9))
//     .then(() => console.log("내가 마지막!"));
//callback

//프로세스 순서 보장

//Promise 미래의 값이 성공(Resolve) 혹은 실패(Reject) 될 것이라는 약속된 결과를 표현

// const promise = new Promise((resolve, reject) => {
//   // if(성공) resolve(결과); 호출
//   // else reject(에러);
// });
// //상태
// // pending : 대기중
// // fulfilled : 성공(resolve 호출된 상태)
// // rejected : 실패(reject가 호출된 상태)
// promise
//     .then(result => {/*성공시*/})
//     .catch(e => reject(e) /*실패시 */)
//     .finally(() => {/*무조건 실행*/})
//
// [].sort((a, b) => {});

function asyncTask(){
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve("완료")}, 500);
  });
}

// const result = asyncTask();
//
// result
//     .then(msg => {
//       console.log(msg);
//     })
//     .catch(err => {
//       console.log(err);
//     });
// asyncTask().then(console.log).catch(console.log)
//Thenable function

// callAsync(0, () => {
//   callAsync(1, () => {
//     callAsync(2, () => {
//       callAsync(3, () => {
//         callAsync(4, () => {
//           callLocal(5, () => {
//             callLocal(6, () => {
//               callLocal(7 , () => {
//                 callLocal(8, () => {
//                   callLocal(9)
//                 })
//               })
//             })
//           })
//         });
//       });
//     });
//   });
// });

// $.ajax({
//   success: (data) => {
//     $.ajax({
//       success: (data) => {
//
//       }
//     })
//   }
// });
// $.ajax({
//   success:(data) => {},
//   error:(error) => {},
//   always:(data) => {}
// })
// .done((data) => {
//
// })
// .fail(error => {
//
// })
// .always((data) => {
//
// })
// .done((data) => {
//
// })

//async, await
async function run () {
  callAsync(0);
  await callAsync(1);
  await callAsync(2);
  await callAsync(3);
  await callAsync(4);
  await callLocal(5);
  await callLocal(6);
  await callLocal(7);
  await callLocal(8);
  console.log("내가 마지막 직전!! await 이후")
  await callLocal(9);
  console.log("내가 마지막!! await 이후")
}
// run();

// function fetchWithCallback(url, callback) {
//   fetch(url)
//       .then(response => response.json())
//       .then(data => {
//         console.log("콜백 결과", url)
//         console.log(data);
//         if(callback) callback();
//       })
// }

function fetchWithCallback(bno) {
  return fetch(`../replies/board/${bno}`)
      .then(response => response.json())
      .then(data => {
        console.log('콜백 결과', bno)
        console.log(data);
      })
}

// fetchWithCallback("../replies/board/3", () => fetchWithCallback("../replies/board/4" , () => fetchWithCallback("../replies/board/5")));

// fetchWithCallback(3)
//     .then(() => fetchWithCallback(4))
//     .then(() => fetchWithCallback(5))
//     .then(() => console.log("Promise Call 적용"));

(async () => {
  await fetchWithCallback(3);
  await fetchWithCallback(4);
  await fetchWithCallback(5);
  console.log('IIFE + Await Call 적용');
})(); //IIFE