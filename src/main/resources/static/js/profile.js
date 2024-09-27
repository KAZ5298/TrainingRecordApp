// 指定されたフィールドの disabled 属性を解除
function enableEdit(fieldId) {
    const inputField = document.getElementById(fieldId);
    inputField.disabled = false;
    document.getElementById("confirmBtn").disabled = false;  // 確認ボタンを有効化
}

// 送信前に全てのdisabledを解除
//document.getElementById("updateForm").addEventListener("submit", function() {
//    document.querySelectorAll('input[disabled], select[disabled]').forEach(function(element) {
//        element.disabled = false;
//    });
//});

// 生年月日から年齢を計算
document.getElementById('birthdate').addEventListener('change', function() {
    const birthdateValue = this.value;
    if (birthdateValue) {
        const birthdate = new Date(birthdateValue);
        const today = new Date();
        let age = today.getFullYear() - birthdate.getFullYear();
        const monthDifference = today.getMonth() - birthdate.getMonth();
        if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < birthdate.getDate())) {
            age--;
        }
        document.getElementById('age').value = age;
    } else {
        document.getElementById('age').value = '';
    }
});

// 年齢から生年月日を計算
document.addEventListener('DOMContentLoaded', function() {
    // DBから取得した年齢の値（サーバーサイドで年齢を埋め込む）
    const ageValue = document.getElementById('age').value;
    
    if (ageValue) {
        const today = new Date();
        const birthYear = today.getFullYear() - ageValue;
        const birthdate = new Date(birthYear, today.getMonth(), today.getDate());
        
        // 生年月日フィールドに計算した日付を設定
        document.getElementById('birthdate').value = birthdate.toISOString().split('T')[0];
    }
});

// プロフィール画像のプレビュー表示
document.getElementById('profile_image').addEventListener('change', function(event) {
    const file = event.target.files[0];
    const reader = new FileReader();
    
    reader.onload = function(e) {
        const image = document.getElementById('modalProfileImage');
        image.src = e.target.result;
        image.style.display = 'block'; // プレビューを表示
        document.getElementById('modalProfileImageText').textContent = "";
    };
    
    if (file) {
        reader.readAsDataURL(file);
    } else {
        document.getElementById('modalProfileImage').src = "";
        document.getElementById('modalProfileImage').style.display = "none";
        document.getElementById('modalProfileImageText').textContent = "画像選択なし";
    }
});

// 入力内容をモーダルに表示する
function showModal() {
    document.getElementById("modalName").textContent = document.getElementById("name").value;
    document.getElementById("modalEmail").textContent = document.getElementById("email").value;
    document.getElementById("modalFamilyName").textContent = document.getElementById("family_name").value;
    document.getElementById("modalFirstName").textContent = document.getElementById("first_name").value;
    document.getElementById("modalAge").textContent = document.getElementById("age").value;
    document.getElementById("modalWeight").textContent = document.getElementById("weight").value;
    document.getElementById("modalHeight").textContent = document.getElementById("height").value;
    const genderText = document.getElementById("gender").options[document.getElementById("gender").selectedIndex].text;
    document.getElementById("modalGender").textContent = genderText;
}

// バリデーションチェック
function validateForm() {
    const form = document.getElementById("profileForm");
    let isValid = true; // バリデーション結果を追跡する変数
    
    // 入力値の取得
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const family_name = document.getElementById('family_name').value.trim();
    const first_name = document.getElementById('first_name').value.trim();
    const birthdate = document.getElementById('birthdate').value.trim();
    const age = document.getElementById('age').value.trim();
    const weight = document.getElementById('weight').value.trim();
    const height = document.getElementById('height').value.trim();
    const gender = document.getElementById('gender').value.trim();
    
    // エラーメッセージ要素の初期化
    const nameError = document.getElementById('nameError');
    const emailError = document.getElementById('emailError');
    const family_nameError = document.getElementById('family_nameError');
    const first_nameError = document.getElementById('first_nameError');
    const birthdateError = document.getElementById('birthdateError');
    const ageError = document.getElementById('ageError');
    const weightError = document.getElementById('weightError');
    const heightError = document.getElementById('heightError');
    const genderError = document.getElementById('genderError');
    
    // エラーメッセージのクリア
    nameError.textContent = '';
    emailError.textContent = '';
    family_nameError.textContent = '';
    first_nameError.textContent = '';
    birthdateError.textContent = '';
    ageError.textContent = '';
    weightError.textContent = '';
    heightError.textContent = '';
    genderError.textContent = '';
    
    // メールアドレスの正規表現パターン
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    
    if (name === '') {
        nameError.textContent = 'ユーザー名は入力必須です';
        isValid = false;
    }
    if (email === '') {
        emailError.textContent = 'メールアドレスは入力必須です';
        isValid = false;
    } else if (!emailPattern.test(email)) {
        emailError.textContent = '正しいメールアドレスを入力してください';
        isValid = false;
    }
    if (family_name === '') {
        family_nameError.textContent = '姓は入力必須です';
        isValid = false;
    }
    if (first_name === '') {
        first_nameError.textContent = '名は入力必須です';
        isValid = false;
    }
    if (birthdate === '') {
        birthdateError.textContent = '生年月日は入力必須です';
        isValid = false;
    }
    if (age === '') {
        ageError.textContent = '年齢は入力必須です';
        isValid = false;
    } else if (age <= 0) {
        ageError.textContent = '年齢は正しく入力してください';
        isValid = false;
    }
    if (weight === '') {
        weightError.textContent = '体重は入力必須です';
        isValid = false;
    } else if (weight <= 0) {
        weightError.textContent = '体重は正しく入力してください';
        isValid = false;
    }
    if (height === '') {
        heightError.textContent = '身長は入力必須です';
        isValid = false;
    } else if (height <= 0) {
        heightError.textContent = '身長は正しく入力してください';
        isValid = false;
    }
    if (gender === '') {
        genderError.textContent = '性別は選択必須です';
        isValid = false;
    }
    
    // バリデーションが成功した場合、モーダルを表示
    if (isValid) {
        showModal();  // モーダルにデータを反映
        const modal = new bootstrap.Modal(document.getElementById('confirmationModal'));
        modal.show();  // モーダルを表示
    } else {
        form.reportValidity();  // バリデーションエラーを表示
    }
    
    // バリデーション結果を返す
    return isValid;
}

// フォーム送信時にバリデーション関数を実行
document.getElementById('profileForm').onsubmit = function(event) {
    if (!validateForm()) {
        event.preventDefault(); // バリデーションに失敗した場合、送信を防ぐ
    }
};

// フォームをサブミットする
function submitForm() {
    document.getElementById("profileForm").submit();
}