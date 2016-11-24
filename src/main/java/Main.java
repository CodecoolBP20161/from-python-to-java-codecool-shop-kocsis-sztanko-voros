import com.codecool.shop.controller.Controller;
import com.codecool.shop.dao.JDBCimplementation.SupplierDaoJDBC;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.test.TestData;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());

        staticFileLocation("/public");
        port(8888);

        TestData.populateData();

        post("/remove/:id", Controller::decreaseItem);

        post("/add/:id", Controller::increaseItem);

        get("/:type/:id", Controller::renderProductsByFilter, new ThymeleafTemplateEngine());

        post("/cart/:id", Controller::addCart);

        get("/", Controller::renderMain, new ThymeleafTemplateEngine());

        get("/shoppingcart", Controller::renderCart, new ThymeleafTemplateEngine());

    }

}
