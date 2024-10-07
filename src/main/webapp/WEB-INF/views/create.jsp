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
                            min="0"
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
                            <input type="radio" name="civility" value="madame" checked/>
                            <span class="radio-custom"></span>
                            Madame
                        </label>
                        <label class="radio-label">
                            <input type="radio" name="civility" value="mademoiselle"/>
                            <span class="radio-custom"></span>
                            Mademoiselle
                        </label>
                        <label class="radio-label">
                            <input type="radio" name="civility" value="monsieur"/>
                            <span class="radio-custom"></span>
                            Monsieur
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
            <tr>
                <td class="text-primary"></td>
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
    const interestRate = 12 / 100;

    function updateInputValue(rangeId) {
        const range = document.getElementById(`custom-range${rangeId}`);
        const input = document.getElementById(`range-value${rangeId}`);
        input.value = range.value;
        if (rangeId === 1 || rangeId === 2) {
            calculateMensualites();
        }
    }

    function updateRangeValue(rangeId) {
        const input = document.getElementById(`range-value${rangeId}`);
        const range = document.getElementById(`custom-range${rangeId}`);
        range.value = input.value;
        if (rangeId === 1 || rangeId === 2) {
            calculateMensualites();
        }
    }

    function updateMensualites() {
        const mensualite = parseFloat(
            document.getElementById("range-value3").value
        );
        const n = parseFloat(document.getElementById("range-value2").value);
        const t = interestRate / 12;

        if (mensualite > 0 && n > 0) {
            const K = (mensualite * (1 - Math.pow(1 + t, -n))) / t;
            document.getElementById("range-value1").value = K.toFixed(2);
            document.getElementById("custom-range1").value = K.toFixed(2);
        }
    }

    function calculateMensualites() {
        const K = parseFloat(document.getElementById("range-value1").value);
        const n = parseFloat(document.getElementById("range-value2").value);
        const t = interestRate / 12;

        if (K > 0 && n > 0) {
            const mensualite = (K * t) / (1 - Math.pow(1 + t, -n));
            document.getElementById("range-value3").value = mensualite.toFixed(2);
            document.getElementById("custom-range3").value =
                mensualite.toFixed(2);
        }
    }

    calculateMensualites();

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

</script>
</body>
</html>
