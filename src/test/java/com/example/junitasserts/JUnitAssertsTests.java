package com.example.junitasserts;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

class JUnitAssertsTests {

  @Test
  void addProduct_testingAssertTrue() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);
    assertTrue(fooStore.getProducts().containsKey(1234));
  }

  @Test
  void addThenRemoveProduct_testingAssertFalse() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);
    assertTrue(fooStore.getProducts().containsKey(1234));

    fooStore.removeProduct(1234);
    assertFalse(fooStore.getProducts().containsKey(1234));
  }

  @Test
  void addProduct_testingAssertEquals() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);

    Product actualProduct = fooStore.getProducts().get(1234);
    assertEquals("test", actualProduct.getName());
    assertEquals(9.99, actualProduct.getPrice());
    assertEquals(1, actualProduct.getQuantity());
  }

  @Test
  void addThenAddProduct_testingAssertNotEquals() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);

    Product actualProduct = fooStore.getProducts().get(1234);
    assertEquals(9.99, actualProduct.getPrice());

    FooProduct fooProductSale = new FooProduct(1234, "test", 6.99, 1);
    fooStore.addProduct(fooProductSale);

    Product actualProductSale = fooStore.getProducts().get(1234);
    assertEquals(1, actualProductSale.getQuantity());
    assertNotEquals(9.99, actualProductSale.getPrice()); // Expected: 6.99
  }

  @Test
  void sellProduct_testingAssertEquals() throws InsufficientInventoryException {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 5);

    fooStore.addProduct(fooProduct);
    
    Product actualProduct = fooStore.getProducts().get(1234);
    assertEquals(5, actualProduct.getQuantity());

    fooStore.sellProduct(1234, 5);
    assertEquals(0, actualProduct.getQuantity()); // All products sold!
  }

  @Test
  void addProduct_testingAssertSame() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);

    Product actualProduct = fooStore.getProducts().get(1234);
    assertSame(fooProduct, actualProduct);
  }

  @Test
  void differenceBetweenAssertSameAssertEquals() {
    String str1 = new String("test");
    String str2 = new String("test");

    assertNotSame(str1, str2); // True: not the same String object
    assertEquals(str1, str2); // Also True: value of Strings are the same
  }

  @Test
  void addProduct_testNullAndNotNull() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);

    assertNull(fooStore.getProducts().get(12345));
    assertNotNull(fooStore.getProducts().get(1234));
  }

  @Test
  void sellProduct_testingAssertThrows() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 5);

    fooStore.addProduct(fooProduct);
    
    Product actualProduct = fooStore.getProducts().get(1234);
    assertEquals(5, actualProduct.getQuantity());

    assertThrows(InsufficientInventoryException.class, () -> {
      fooStore.sellProduct(1234, 6);
    });
  }

  @Test
  void addProduct_testingAssertSame_Hamcrest() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);

    Product actualProduct = fooStore.getProducts().get(1234);
    assertThat(actualProduct, is(fooProduct));
  }

  @Test
  void addProduct_testNullAndNotNull_Hamcrest() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);

    assertThat(fooStore.getProducts().get(12345), is(nullValue()));
    assertThat(fooStore.getProducts().get(1234), is(not(nullValue()))); // is(notNullValue()) also okay
  }

  @Test
  void addProduct_verifyAllProperties_assertAll() {
    FooStore fooStore = new FooStore();
    FooProduct fooProduct = new FooProduct(1234, "test", 9.99, 1);

    fooStore.addProduct(fooProduct);

    assertAll("fooProduct should exist and contain the correct parameters",
      () -> assertTrue(fooStore.getProducts().containsKey(1234)),
      () -> assertEquals(1234, fooStore.getProducts().get(1234).getId()),
      () -> assertEquals("test", fooStore.getProducts().get(1234).getName()),
      () -> assertThat(fooStore.getProducts().get(1234).getPrice(), equalTo(9.99)),
      () -> assertThat(fooStore.getProducts().get(1234).getQuantity(), equalTo(1))
    );
  }
}