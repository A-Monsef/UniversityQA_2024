package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import java.util.Random;

public class InventarioSuite {
    public static WebDriver driver;

    public static WebDriverWait wait;

    @Before
    public void setUp() {
        //Paso0
        WebDriverManager.chromedriver().setup(); // Cargar Chromedriver

        driver= new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10, 500);
    }
    @Test
    public void loginTest() {
        driver.get("https://www.saucedemo.com");
        WebElement inputName = driver.findElement(By.id("user-name"));
        inputName.sendKeys("standard_user");
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();
        String ExpectedData = "https://www.saucedemo.com/inventory.html";
        String ActualData = driver.getCurrentUrl();
        if(ActualData.equals(ExpectedData)){
            System.out.println("Valid Test");
        }else {
            System.out.println("Invalid Test. " + "Current Value " + ActualData + "\n" + "and the expected value is: " + ExpectedData);
        }

    }
    @Test
    public void ValidarNumeroProductos() {
        driver.get("https://www.saucedemo.com");
        WebElement inputName = driver.findElement(By.id("user-name"));
        inputName.sendKeys("standard_user");
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();
        try {

            List<WebElement> productos = driver.findElements(By.className("inventory_item"));

            Assert.assertEquals(6, productos.size());
            System.out.println("La cantidad de productos es correcta: 6");
        } catch (AssertionError e) {
            System.out.println("Error: La cantidad de productos no es 6");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error al validar la cantidad de productos");
            e.printStackTrace();
        }

    }
    @Test
    public void ValidarValor() {
        driver.get("https://www.saucedemo.com");
        WebElement inputName = driver.findElement(By.id("user-name"));
        inputName.sendKeys("standard_user");
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();

            WebElement btnAgregarCarrito = driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));

            btnAgregarCarrito.click();

        try {
            // esperamos a que se actualize la shopping cart
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement shoppingCartContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container")));

            // encontramos el elemento especificamenete
            WebElement shoppingCartBadge = shoppingCartContainer.findElement(By.cssSelector(".shopping_cart_badge"));

            // ahora se ha encontrado el badge y procedemos con el test
            String textoNumero = shoppingCartBadge.getText();
            int numero = Integer.parseInt(textoNumero);
            Assert.assertEquals(1, numero);

            System.out.println("¡El número en el shopping-cart-badge se ha actualizado a 1!");
        } catch (Exception e) {
            System.out.println("Error al obtener el elemento o el número no es 1: " + e.getMessage());
        }





    }
    @Test
    public void TestShoppingCard(){
        driver.get("https://saucedemo.com");
        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPass =driver.findElement(By.id("password"));
        inputPass.sendKeys("secret_sauce");

        WebElement LogBtn = driver.findElement(By.id("login-button"));
        LogBtn.click();

        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCart.click();

        WebElement NumberCart = null;
        String number = "0";

        try{
            NumberCart = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
            number = NumberCart.getText();
        }catch(NoSuchElementException nsee ){
            number = null;
        }
        Assert.assertEquals("Prueba añadir carrito invalida", 1, Integer.parseInt(number));
    }
    @Test
    public void EliminateProduct(){
        driver.get("https://saucedemo.com");
        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPass =driver.findElement(By.id("password"));
        inputPass.sendKeys("secret_sauce");

        WebElement LogBtn = driver.findElement(By.id("login-button"));
        LogBtn.click();

        WebElement addToCartBoltTShirt = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addToCartBoltTShirt.click();

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        WebElement removeFromCartBoltTShirt = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
        removeFromCartBoltTShirt.click();

        driver.get("https://saucedemo.com/inventory.html");

        String expectedNumber = "0";
        String actualNumber = "1";

        try {
            WebElement NumberCartAfterRemoval = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
            actualNumber = NumberCartAfterRemoval.getText();
        } catch(NoSuchElementException nsee) {
            actualNumber = "0"; //
        }

        Assert.assertEquals("El producto no fue eliminado correctamente del carrito", expectedNumber, actualNumber);
        System.out.println("El producto ha sido eliminado correctamente del carrito.");
    }

    @Test
    public void EliminarProducto() {
        driver.get("https://www.saucedemo.com");
        WebElement inputName = driver.findElement(By.id("user-name"));
        inputName.sendKeys("standard_user");
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();
        try {
            WebElement btnAgregarCarrito = driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']"));
            btnAgregarCarrito.click();
            Thread.sleep(2000);

            WebElement btnEliminar = driver.findElement(By.cssSelector("[data-test='remove-sauce-labs-onesie']"));
            Assert.assertTrue(btnEliminar.isDisplayed());

            System.out.println("El producto se ha agregado correctamente al carrito. Se visualiza el botón REMOVE.");

        } catch (Exception e) {

            System.out.println("Error al agregar el producto al carrito o al validar el botón REMOVE");
            e.printStackTrace();

        }

    }
    @Test
    public void EliminarProductoCarrito() throws InterruptedException {
        driver.get("https://www.saucedemo.com");
        WebElement inputName = driver.findElement(By.id("user-name"));
        inputName.sendKeys("standard_user");
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();

        WebElement btnAgregarCarrito = driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']"));
        btnAgregarCarrito.click();
        Thread.sleep(2000);

        WebElement btnEliminar = driver.findElement(By.cssSelector("[data-test='remove-sauce-labs-onesie']"));
        Assert.assertTrue(btnEliminar.isDisplayed());

        System.out.println("El producto se ha agregado correctamente al carrito. Se visualiza el botón REMOVE.");

    }

    @Test
    public void AgregarAlCarritoTresProductos() {

            driver.get("https://www.saucedemo.com");


            WebElement usernameInput = driver.findElement(By.id("user-name"));
            usernameInput.sendKeys("standard_user");
            WebElement passwordInput = driver.findElement(By.id("password"));
            passwordInput.sendKeys("secret_sauce");
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

    }
    @Test
    public void testLoginAndFilter() {
        driver.get("https://www.saucedemo.com");
        WebElement inputName = driver.findElement(By.id("user-name"));
        inputName.sendKeys("standard_user");
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();
    }

    @Test
    public void Ejerciciojunit(){
        String[] nombresEsperados = {"java ","junit","jboss"};
        String[] nombres;
    }

    @After
    public void tearDown() {
    //    driver.close();
    }
}

