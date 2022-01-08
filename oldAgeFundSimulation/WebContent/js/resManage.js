// 削除時の確認
document.delConfirm.btn1.addEventListener('click', function() {
    result = window.confirm('削除してもよいですか？');
    if(result) {
        document.delConfirm.action = "/oldAgeFundSimulation/DeleteResServlet";
    } else {
        document.delConfirm.action = "/oldAgeFundSimulation/ResManageServlet";
    }
})
// チェックマーク付加時のみ削除ボタンアクティブ化
$(function(){
    // 初期状態のボタンは無効
    $("#btn2").prop("disabled", true);
    // チェックボックスの状態が変わったら（クリックされたら）
    $("input[type='checkbox']").on('change', function () {
        // チェックされているチェックボックスの数
        if ($(".chk:checked").length > 0) {
            // ボタン有効
            $("#btn2").prop("disabled", false);
        } else {
            // ボタン無効
            $("#btn2").prop("disabled", true);
        }
    });
});
//「全選択」のチェックボックス
let checkAll = document.getElementById("checkAll");
//「全選択」以外のチェックボックス
let el = document.getElementsByClassName("chk");
//全てのチェックボックスをON/OFFする
const funcCheckAll = (bool) => {
    for (let i = 0; i < el.length; i++) {
        el[i].checked = bool;
    }
}
//「checks」のclassを持つ要素のチェック状態で「全選択」のチェック状態をON/OFFする
const funcCheck = () => {
    let count = 0;
    for (let i = 0; i < el.length; i++) {
        if (el[i].checked) {
            count += 1;
        }
    }
    if (el.length === count) {
        checkAll.checked = true;
    } else {
        checkAll.checked = false;
    }
};
//「全選択」のチェックボックスをクリックした時
checkAll.addEventListener("click",() => {
    funcCheckAll(checkAll.checked);
},false);
//「全選択」以外のチェックボックスをクリックした時
for (let i = 0; i < el.length; i++) {
    el[i].addEventListener("click", funcCheck, false);
}