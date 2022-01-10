package cloud.autotests.tests;

import cloud.autotests.helpers.DriverUtils;
import com.codeborne.pdftest.PDF;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class VTBTests extends TestBase {
    @Test
    @DisplayName("Открываем страницы ВТБ и проверяем контент")
    public void testsForVTBOpenPagesAndCheckedContent() {

        step("Открываем главную страницу ВТБ", () -> {
            open(URL);
            step("Проверяем, что шапка содержит элемент" + checkedElement1, () -> {
                $(".header-bottom-menu").shouldHave(text(checkedElement1));
            });
            step("Переходим на вкладку `Крупный бизнес` и проверяем содержимое вкладки" + nameObject1, () -> {
                $$(".link__text.text.color-white.size-small-medium.weight-medium").find(text("Крупный бизнес")).click();
                $(".tab-panel__image").scrollTo();
                $$(".tab-panel__row li").find(text(nameObject1)).click();
                step("Проверяем вкладку на содержание текста " + checkedText1, () -> {
                    $(".tab-panel__wrap").shouldHave(text(checkedText1));
                });
            });
            step("Проверяем содержимое вкладки" + nameObject2, () -> {
                $$(".tab-panel__row li").find(text(nameObject2)).click();
                step("Проверяем вкладку на содержание текста " + checkedText2, () -> {
                    $(".tab-panel__wrap").shouldHave(text(checkedText2));
                });
            });
            step("Проверяем содержимое вкладки" + nameObject3, () -> {
                $$(".tab-panel__row li").find(text(nameObject3)).click();
                step("Проверяем вкладку на содержание текста " + checkedText3, () -> {
                    $(".tab-panel__wrap").shouldHave(text(checkedText3));
                });
            });
        });
    }

    @Test
    @DisplayName("Открываем главную страницу ВТБ, заполняем конвертер валют, проверяем содержимое при нажатии кнопки `Найти отделение` ")
    public void openPaymentPageAndCheckedCurrencyRate() {

        step("Открываем главную страницу и переходим в `Платежи и Переводы`", () -> {
            open(URL);
            $$(".header-bottom-menu-item").findBy(text("Платежи и переводы")).click();
        });
        step("Переходим на страницу `Обмен валюты`", () ->
                $$(".header-menu-panel-group-item").findBy(text("Обмен валюты")).click());
        step("Выбираем способ обмена", () -> {
            $(".typographystyles__Box-section-calculator__sc-a16j0s-0.eNmBzJ").scrollTo();
            $(".select-inputstyles__SelectInputInner-currency-converter__sc-exkbxt-1.bmsPyc").click();
            $$(".ScrollbarsCustom.trackYVisible").findBy(text("В офисе (безналично)")).click();
        });
        step("Нажимаем на кнопку `Найти отделение`", () ->
                $$(".big-buttonstyles__Wrapper-currency-converter__sc-4w7jjq-5.jFzfnT").find(text("Найти отделение")).click());
        step("Проверяем, что страница содержит элемент" + checkedElement2, () ->
                $(".ng-scope").shouldHave(text(checkedElement2)));
    }

    @Test
    @DisplayName("Скачиваем PDF документ и проверяем содержимое")
    public void downloadFiles() throws IOException {
        step("Скачиваем PDF документ", () -> {
            open(URL);
            $(".link__text.text.color-dark-gray.size-small-medium.weight-normal").scrollTo();
            $(By.linkText("Раскрытие информации профессиональным участником рынка ценных бумаг")).click();
            File pdf = $(".docs-items__doc.docs-items__doc_pdf.docs-items__doc-br").scrollTo().download();
            PDF parsedPdf = new PDF(pdf);
            step("Проверяем содержимое", () ->
                    Assertions.assertEquals(2, parsedPdf.numberOfPages));
        });
    }

    @Test
    @DisplayName("Меняем язык на сайте и проверяем содержимое")
    public void changeLanguage() {
        step("Выбираем английский язык", () -> {
            open(URL);
            $(".location").scrollTo().find(By.linkText(checkedElement3)).click();
            step("Проверяем содержимое", () ->
                    $(".navigation__second__menu").shouldHave(text(checkedText4)));
        });
    }

}
