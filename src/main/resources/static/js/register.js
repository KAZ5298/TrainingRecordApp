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
    const form = document.getElementById("registerForm");
    let isValid = true; // バリデーション結果を追跡する変数
    
    // 入力値の取得
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();
    const password_confirm = document.getElementById('password_confirm').value.trim();
    const family_name = document.getElementById('family_name').value.trim();
    const first_name = document.getElementById('first_name').value.trim();
    const birthdate = document.getElementById('birthdate').value.trim();
    const age = document.getElementById('age').value.trim();
    const weight = document.getElementById('weight').value.trim();
    const height = document.getElementById('height').value.trim();
    const gender = document.getElementById('gender').value.trim();
    
    // エラーメッセージ要素の初期化
    const frontendNameError = document.getElementById('frontendNameError');
    const frontendEmailError = document.getElementById('frontendEmailError');
    const frontendPasswordError = document.getElementById('frontendPasswordError');
    const frontendPassword_confirmError = document.getElementById('frontendPassword_confirmError');
    const frontendFamily_nameError = document.getElementById('frontendFamily_nameError');
    const frontendFirst_nameError = document.getElementById('frontendFirst_nameError');
    const frontendBirthdateError = document.getElementById('frontendBirthdateError');
    const frontendAgeError = document.getElementById('frontendAgeError');
    const frontendWeightError = document.getElementById('frontendWeightError');
    const frontendHeightError = document.getElementById('frontendHeightError');
    const frontendGenderError = document.getElementById('frontendGenderError');
    
    // エラーメッセージのクリア
    frontendNameError.textContent = '';
    frontendEmailError.textContent = '';
    frontendPasswordError.textContent = '';
    frontendPassword_confirmError.textContent = '';
    frontendFamily_nameError.textContent = '';
    frontendFirst_nameError.textContent = '';
    frontendBirthdateError.textContent = '';
    frontendAgeError.textContent = '';
    frontendWeightError.textContent = '';
    frontendHeightError.textContent = '';
    frontendGenderError.textContent = '';
    
    // メールアドレスの正規表現パターン
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    
    if (name === '') {
        frontendNameError.textContent = 'ユーザー名は入力必須です';
        isValid = false;
    }
    if (email === '') {
        frontendEmailError.textContent = 'メールアドレスは入力必須です';
        isValid = false;
    } else if (!emailPattern.test(email)) {
        frontendEmailError.textContent = '正しいメールアドレスを入力してください';
        isValid = false;
    }
    if (password === '') {
        frontendPasswordError.textContent = 'パスワードは入力必須です';
        isValid = false;
    }
    if (password_confirm === '') {
        frontendPassword_confirmError.textContent = 'パスワード確認は入力必須です';
        isValid = false;
    } else if (password !== password_confirm) {
        frontendPassword_confirmError.textContent = 'パスワードが一致しません';
        isValid = false;
    }
    if (family_name === '') {
        frontendFamily_nameError.textContent = '姓は入力必須です';
        isValid = false;
    }
    if (first_name === '') {
        frontendFirst_nameError.textContent = '名は入力必須です';
        isValid = false;
    }
    if (birthdate === '') {
        frontendBirthdateError.textContent = '生年月日は入力必須です';
        isValid = false;
    }
    if (age === '') {
        frontendAgeError.textContent = '年齢は入力必須です';
        isValid = false;
    } else if (age <= 0) {
        frontendAgeError.textContent = '年齢は正しく入力してください';
        isValid = false;
    }
    if (weight === '') {
        frontendWeightError.textContent = '体重は入力必須です';
        isValid = false;
    } else if (weight <= 0) {
        frontendWeightError.textContent = '体重は正しく入力してください';
        isValid = false;
    }
    if (height === '') {
        frontendHeightError.textContent = '身長は入力必須です';
        isValid = false;
    } else if (height <= 0) {
        frontendHeightError.textContent = '身長は正しく入力してください';
        isValid = false;
    }
    if (gender === '') {
        frontendGenderError.textContent = '性別は選択必須です';
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
document.getElementById('registerForm').onsubmit = function(event) {
    if (!validateForm()) {
        event.preventDefault(); // バリデーションに失敗した場合、送信を防ぐ
    }
};

// フォームをサブミットする
function submitForm() {
    document.getElementById("registerForm").submit();
}