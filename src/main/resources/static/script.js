
const API = "http://localhost:8080";


/* =========================
   NAVIGATION
========================= */

function showSection(sectionId) {

    document.querySelectorAll(".section")
        .forEach(section => {
            section.classList.remove("active");
        });

    document.getElementById(sectionId)
        .classList.add("active");
}


/* =========================
   RECIPES
========================= */

async function addRecipe() {

    const recipe = {

        name: document.getElementById("recipeName").value,

        cuisine: document.getElementById("cuisine").value,

        tags: document.getElementById("tags").value,

        prepTime:
            Number(document.getElementById("prepTime").value),

        steps:
            document.getElementById("steps").value,

        favourite: false,

        ingredients: [
            {
                ingredientName:
                    document.getElementById("ingredientName").value,

                quantity:
                    Number(document.getElementById("quantity").value),

                unit:
                    document.getElementById("unit").value
            }
        ]
    };


    try {

        const response = await fetch(
            `${API}/recipes/createRecipe`,
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(recipe)
            }
        );

        const data = await response.json();

        alert("Recipe added successfully!");

        displayRecipes([data]);

    } catch (error) {

        console.error(error);

        alert("Could not connect to Spring Boot backend.");
    }
}


/* Get all recipes */

async function getRecipes() {

    try {

        const response =
            await fetch(`${API}/recipes/getRecipes`);

        const recipes =
            await response.json();

        displayRecipes(recipes);

    } catch (error) {

        console.error(error);

        alert("Could not load recipes.");
    }
}


/* Search recipe */

async function searchRecipes() {

    const search =
        document.getElementById("searchText").value;

    if (!search) {

        getRecipes();
        return;
    }

    try {

        const response =
            await fetch(
                `${API}/recipes/searchByName?name=${encodeURIComponent(search)}`
            );

        const recipes =
            await response.json();

        displayRecipes(recipes);

    } catch (error) {

        console.error(error);
    }
}


/* Display recipes */

function displayRecipes(recipes) {

    const container =
        document.getElementById("recipeList");

    container.innerHTML = "";


    if (recipes.length === 0) {

        container.innerHTML =
            "<p>No recipes found.</p>";

        return;
    }


    recipes.forEach(recipe => {

        let ingredientsHTML = "";

        if (recipe.ingredients) {

            recipe.ingredients.forEach(ingredient => {

                ingredientsHTML += `
                    <li>
                        ${ingredient.ingredientName}
                        - ${ingredient.quantity}
                        ${ingredient.unit}
                    </li>
                `;
            });
        }


        container.innerHTML += `

            <div class="recipe-card">

                <h3>
                    ${recipe.name}
                </h3>

                <p>
                    <strong>Cuisine:</strong>
                    ${recipe.cuisine}
                </p>

                <p>
                    <strong>Tags:</strong>
                    ${recipe.tags}
                </p>

                <p>
                    <strong>Prep Time:</strong>
                    ${recipe.prepTime} minutes
                </p>

                <p>
                    <strong>Steps:</strong>
                    ${recipe.steps}
                </p>

                <p>
                    <strong>Ingredients:</strong>
                </p>

                <ul>
                    ${ingredientsHTML}
                </ul>

                <p class="favourite">
                    ${recipe.favourite ? "❤️ Favourite" : "♡ Not Favourite"}
                </p>

                <button
                    onclick="toggleFavourite(${recipe.id})">
                    ${recipe.favourite ? "Remove Favourite" : "Add Favourite"}
                </button>

            </div>
        `;
    });
}


/* Favourite */

async function toggleFavourite(id) {

    try {

        const response =
            await fetch(
                `${API}/recipes/markFavourite/${id}`,
                {
                    method: "PUT"
                }
            );

        const recipe =
            await response.json();

        alert(
            recipe.favourite
                ? "Added to favourites!"
                : "Removed from favourites!"
        );

        getRecipes();

    } catch (error) {

        console.error(error);
    }
}


/* =========================
   MEAL PLAN
========================= */

async function createMealPlan() {

    const date =
        document.getElementById("mealDate").value;

    const type =
        document.getElementById("mealType").value;

    const recipeId =
        Number(document.getElementById("mealRecipeId").value);


    const mealPlan = {

        mealDate: date,

        mealType: type,

        recipe: {
            id: recipeId
        }
    };


    try {

        const response =
            await fetch(
                `${API}/mealPlans/createMealPlan`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(mealPlan)
                }
            );

        const data =
            await response.json();

        alert("Meal planned successfully!");

        console.log(data);

    } catch (error) {

        console.error(error);

        alert("Could not create meal plan.");
    }
}


/* Get Meal Plans */

async function getMealPlans() {

    try {

        const response =
            await fetch(
                `${API}/mealPlans/getMealPlans`
            );

        const mealPlans =
            await response.json();

        const container =
            document.getElementById("mealPlanList");

        container.innerHTML = "";


        mealPlans.forEach(plan => {

            container.innerHTML += `

                <div class="list-card">

                    <strong>
                        ${plan.mealDate}
                    </strong>

                    - ${plan.mealType}

                    <br>

                    Recipe:
                    ${plan.recipe
                        ? plan.recipe.name
                        : "No recipe"}

                    <br>

                    Recipe ID:
                    ${plan.recipe
                        ? plan.recipe.id
                        : "-"}

                </div>

            `;
        });

    } catch (error) {

        console.error(error);
    }
}


/* =========================
   SHOPPING LIST
========================= */

async function generateShoppingList() {

    const startDate =
        document.getElementById("startDate").value;

    const endDate =
        document.getElementById("endDate").value;


    try {

        const response =
            await fetch(
                `${API}/shoppingList/generateShoppingList?startDate=${startDate}&endDate=${endDate}`
            );

        const list =
            await response.json();


        const container =
            document.getElementById("shoppingList");

        container.innerHTML = `
            <h3>Required Ingredients</h3>
        `;


        if (list.length === 0) {

            container.innerHTML +=
                "<p>No ingredients found for this period.</p>";

            return;
        }


        list.forEach(item => {

            container.innerHTML += `

                <div class="list-card">

                    🛒
                    <strong>
                        ${item.ingredientName}
                    </strong>

                    -

                    ${item.quantity}
                    ${item.unit}

                </div>

            `;
        });

    } catch (error) {

        console.error(error);

        alert("Could not generate shopping list.");
    }
}


/* =========================
   USERS
========================= */

async function createUser() {

    const user = {

        name:
            document.getElementById("userName").value,

        email:
            document.getElementById("userEmail").value,

        password:
            document.getElementById("userPassword").value
    };


    try {

        const response =
            await fetch(
                `${API}/users/createUser`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(user)
                }
            );

        await response.json();

        alert("User created successfully!");

    } catch (error) {

        console.error(error);

        alert("Could not create user.");
    }
}


/* Get Users */

async function getUsers() {

    try {

        const response =
            await fetch(
                `${API}/users/getUsers`
            );

        const users =
            await response.json();

        const container =
            document.getElementById("userList");

        container.innerHTML = "";


        users.forEach(user => {

            container.innerHTML += `

                <div class="list-card">

                    <strong>
                        ${user.name}
                    </strong>

                    <br>

                    ${user.email}

                </div>

            `;
        });

    } catch (error) {

        console.error(error);
    }
}


/* =========================
   INITIAL LOAD
========================= */

getRecipes();

