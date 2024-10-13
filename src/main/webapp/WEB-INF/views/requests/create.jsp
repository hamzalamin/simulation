<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WAFA SALAF</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style1.css">
</head>
<body>
<button type="button" class="prev-step">⬅️ Retour</button>
<section style="display: flex">
    <div>
        <div class="steps">
            <div class="step" id="step-0">
                <span class="step-number">1</span>
                <span class="step-title">Simuler mon crédit</span>
            </div>
            <div class="step" id="step-1">
                <span class="step-number">2</span>
                <span class="step-title">Mes coordonnées</span>
            </div>
            <div class="step" id="step-2">
                <span class="step-number">3</span>
                <span class="step-title">Mes infos personnelles</span>
            </div>
        </div>
        <form id="multi-step-form" action="${pageContext.request.contextPath}/requests" method="post">
            <input type="hidden" name="action" value="create"/>
            <div id="step-content-0" class="step-content">
                <h4>Mon Project</h4>
                <select type="text" id="project_name" name="project_name" required>
                    <option value="">Chose an option</option>
                    <option value="J’ai besoin d’argent">J’ai besoin d’argent</option>
                    <option value="Je finance mon véhicule d’occasion">Je finance mon véhicule d’occasion</option>
                    <option value="Je Gère mes imprévus">Je Gère mes imprévus</option>
                    <option value="Je finance mon véhicule neuf">Je finance mon véhicule neuf</option>
                    <option value="J’équipe ma maison">J’équipe ma maison</option>
                </select>

                <h4>Je suis</h4>
                <select type="text" id="user_type" name="user_type" required>
                    <option value="">Chose an option</option>
                    <option value="Salarié du secteur privé">Salarié du secteur privé</option>
                    <option value="Fonctionnaire">Fonctionnaire</option>
                    <option value="Profession libérale">Profession libérale</option>
                    <option value="Commerçant">Commerçant</option>
                    <option value="Artisan">Artisan</option>
                    <option value="Retraité">Retraité</option>
                    <option value="Autres professions">Autres professions</option>
                </select>

                <div class="range-container">
                    <label for="custom-range1">Montant (en DH)</label>
                    <div>
                        <input
                                class="range-value"
                                type="number"
                                id="range-value1"
                                name="loan_amount"
                                value="50000"
                                oninput="updateRangeValue(1)"
                                required
                        />
                    </div>
                    <input
                            type="range"
                            id="custom-range1"
                            min="1000"
                            max="1000000"
                            value="50000"
                            name="loan_duration"
                            oninput="updateInputValue(1)"
                            required
                    />
                </div>

                <div class="range-container">
                    <label for="custom-range2">Durée (en mois)</label>
                    <div>
                        <input
                                class="range-value"
                                type="number"
                                id="range-value2"
                                value="60"
                                oninput="updateRangeValue(2)"
                                name="monthly_payment"
                                required
                        />
                    </div>
                    <input
                            type="range"
                            id="custom-range2"
                            min="1"
                            max="360"
                            value="60"
                            oninput="updateInputValue(2)"
                    />
                </div>

                <div class="range-container">
                    <label for="custom-range3">Mensualités (en DH)</label>
                    <div>
                        <input
                                class="range-value"
                                type="number"
                                id="range-value3"
                                value="1000"
                                oninput="updateRangeValue(3)"
                        />
                    </div>
                    <input
                            type="range"
                            id="custom-range3"
                            min="0"
                            max="50000"
                            value="1000"
                            oninput="updateInputValue(3)"
                    />
                </div>

                <div class="btn">
                    <button type="button" class="next-step">Continuer<br><span class="subtext">Sans engagement</span>
                    </button>
                </div>
            </div>
            <div id="step-content-1" class="step-content">
                <div class="input-container">
                    <input type="email" id="email" name="email" required/>
                    <label for="email">Email*</label>
                </div>

                <div class="input-container">
                    <input type="tel" id="phone" name="phone" required/>
                    <label for="phone">Téléphone mobile*</label>
                </div>
                <div class="btn">
                    <button type="button" class="next-step">Continue</button>
                </div>
            </div>

            <div id="step-content-2" class="step-content">
                <div class="title-selection">
                    <p>Civilité</p>
                    <div class="radio-group">
                        <label class="radio-label">
                            <input type="radio" name="civility" value="MADAM" checked/>
                            <span class="radio-custom"></span>
                            MADAM
                        </label>
                        <label class="radio-label">
                            <input type="radio" name="civility" value="MISS"/>
                            <span class="radio-custom"></span>
                            MISS
                        </label>
                        <label class="radio-label">
                            <input type="radio" name="civility" value="SIR"/>
                            <span class="radio-custom"></span>
                            SIR
                        </label>
                    </div>
                    <div class="input-container" style="margin-top: 20px">
                        <input type="text" id="name" name="l_name" required/>
                        <label for="name"> Nome*</label>
                    </div>
                </div>
                <div class="input-container">
                    <input type="text" id="firstname" name="f_name" required/>
                    <label for="firstname"> Prenome*</label>
                </div>
                <div class="input-container">
                    <input type="text" id="cin" name="cin" required/>
                    <label for="cin"> Numéro CIN / Carte de séjour*</label>
                </div>
                <div class="input-container">
                    <input type="date" id="birth_date" name="birth_date" required/>
                    <label for="birth_date"> Date de naissance*</label>
                </div>
                <div class="input-container">
                    <input
                            type="date" id="local_date" name="local_date" required
                    />
                    <label for="local_date">
                        Date d'embauche/début de l'activité*</label
                    >
                </div>
                <div class="input-container">
                    <input
                            type="number"
                            id="monthlyIncome"
                            name="monthlyIncome"
                            required
                    />
                    <label for="monthlyIncome">
                        Total revenus mensuels (net en DH)*</label
                    >
                </div>

                <h4>Avez vous des crédits en cours ?</h4>
                <div class="radio-group">
                    <label class="radio-label">
                        <input type="radio" name="has_credits" value="true" checked/>
                        <span class="radio-custom"></span>
                        oui
                    </label>
                    <label class="radio-label">
                        <input type="radio" name="has_credits" value="false"/>
                        <span class="radio-custom"></span>
                        non
                    </label>
                </div>

                <div class="btn">
                    <button class="next-step" type="submit">Demende ce credit</button>
                    <button
                            type="submit"
                            class="next-step"
                            style="background: #02afbc; margin-left: 5px"
                    >
                        Envoyer
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="container--right">
        <h2>Mon récapitulatif</h2>
        <table class="recap-table">
            <thead>
            <tr>
                <th class="table-primary">my project</th>
            </tr>
            </thead>
            <tbody>
            <tr style="display: inline-grid;">
                <td id="recap-project"></td>
                <td id="recap-user-type"></td>
                <td id="recap-loan-amount"></td>
                <td id="recap-loan-duration"></td>
                <td id="recap-monthly-payment"></td>
                <td id="recap-email"></td>
                <td id="recap-phone"></td>
                <td id="recap-civility"></td>
                <td id="recap-name"></td>
                <td id="recap-firstname"></td>
                <td id="recap-cin"></td>
                <td id="recap-monthly-income"></td>
                <td id="recap-credits"></td>
            </tr>
            </tbody>
        </table>
    </div>
</section>

<script src="script.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        let currentStep = 0;
        const steps = document.querySelectorAll(".step");
        const stepContents = document.querySelectorAll(".step-content");

        function showStep(step) {
            steps.forEach((s, index) => {
                s.classList.toggle("active", index === step);
            });
            stepContents.forEach((content, index) => {
                content.classList.toggle("active", index === step);
            });
            currentStep = step;
        }

        document.addEventListener("click", function (e) {
            if (e.target.classList.contains("next-step")) {
                if (currentStep < steps.length - 1) {
                    showStep(currentStep + 1);
                }
            } else if (e.target.classList.contains("prev-step")) {
                if (currentStep > 0) {
                    showStep(currentStep - 1);
                }
            }
        });

        showStep(currentStep);
    });
</script>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const amountRange = document.querySelector("#custom-range1");
        const amountInput = document.querySelector("#range-value1");
        const durationRange = document.querySelector("#custom-range2");
        const durationInput = document.querySelector("#range-value2");
        const monthlyRange = document.querySelector("#custom-range3");
        const monthlyInput = document.querySelector("#range-value3");

        const taxRate = 12;
        const monthlyRate = taxRate / 12 / 100;

        const updateCalculations = () => {
            let amount = parseFloat(amountRange.value);
            let duration = parseInt(durationRange.value);
            let monthlyPayment = (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -duration));

            monthlyInput.value = monthlyPayment.toFixed(2);
            monthlyRange.value = monthlyPayment.toFixed(2);
        };

        amountRange.addEventListener("input", () => {
            amountInput.value = amountRange.value;
            updateCalculations();
        });

        durationRange.addEventListener("input", () => {
            durationInput.value = durationRange.value;
            updateCalculations();
        });

        monthlyRange.addEventListener("input", () => {
            monthlyInput.value = monthlyRange.value;
        });

        document.querySelector("#loan-form").addEventListener("submit", (e) => {
            e.preventDefault();
            const amount = amountRange.value;
            const duration = durationRange.value;
            const monthlyPayment = monthlyInput.value;
            console.log(`Amount: ${amount}, Duration: ${duration}, Monthly Payment: ${monthlyPayment}`);
        });
    });

    document.addEventListener("DOMContentLoaded", function () {
        let currentStep = 0;
        const steps = document.querySelectorAll(".step");
        const stepContents = document.querySelectorAll(".step-content");

        function showStep(step) {
            steps.forEach((s, index) => {
                s.classList.toggle("active", index === step);
            });
            stepContents.forEach((content, index) => {
                content.classList.toggle("active", index === step);
            });
            currentStep = step;

            updateContinueButton();
        }

        function updateContinueButton() {
            const nextStepButton =
                stepContents[currentStep].querySelector(".next-step");
            const inputs =
                stepContents[currentStep].querySelectorAll("input[required]");
            let allFilled = true;

            inputs.forEach((input) => {
                if (
                    !input.value.trim() ||
                    (input.type === "email" && !validateEmail(input.value))
                ) {
                    allFilled = false;
                }
            });

            nextStepButton.disabled = !allFilled;
        }

        document.addEventListener("input", updateContinueButton);
        document.addEventListener("click", function (e) {
            if (e.target.classList.contains("next-step")) {
                if (currentStep < steps.length - 1) {
                    showStep(currentStep + 1);
                }
            } else if (e.target.classList.contains("prev-step")) {
                if (currentStep > 0) {
                    showStep(currentStep - 1);
                }
            }
        });

        showStep(currentStep);
    });

    function validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(String(email).toLowerCase());
    }


    function updateRecap(fieldId, recapId) {
        const input = document.getElementById(fieldId);
        const recap = document.getElementById(recapId);

        recap.textContent = input.value;
    }

    function updateRangeValue(stepNumber) {
        updateRecap('range-value' + stepNumber, 'recap-loan-amount');
    }

    function updateInputValue(stepNumber) {
        updateRecap('range-value' + stepNumber, stepNumber === 1 ? 'recap-loan-amount' : 'recap-loan-duration');
    }

    document.getElementById('project_name').addEventListener('change', function() {
        updateRecap('project_name', 'recap-project');
    });

    document.getElementById('user_type').addEventListener('change', function() {
        updateRecap('user_type', 'recap-user-type');
    });

    document.getElementById('email').addEventListener('input', function() {
        updateRecap('email', 'recap-email');
    });

    document.getElementById('phone').addEventListener('input', function() {
        updateRecap('phone', 'recap-phone');
    });

    document.getElementsByName('civility').forEach(radio => {
        radio.addEventListener('change', function() {
            const selected = document.querySelector('input[name="civility"]:checked');
            document.getElementById('recap-civility').textContent = selected.value;
        });
    });

    document.getElementById('name').addEventListener('input', function() {
        updateRecap('name', 'recap-name');
    });

    document.getElementById('firstname').addEventListener('input', function() {
        updateRecap('firstname', 'recap-firstname');
    });

    document.getElementById('cin').addEventListener('input', function() {
        updateRecap('cin', 'recap-cin');
    });

    document.getElementById('monthlyIncome').addEventListener('input', function() {
        updateRecap('monthlyIncome', 'recap-monthly-income');
    });

    document.getElementsByName('has_credits').forEach(radio => {
        radio.addEventListener('change', function() {
            const selected = document.querySelector('input[name="has_credits"]:checked');
            document.getElementById('recap-credits').textContent = selected.value === 'true' ? 'oui' : 'non';
        });
    });


</script>
</body>
</html>