# Java Project 78 - Data Validator Library

[![Hexlet Check](https://github.com/VasylP0/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/VasylP0/java-project-78/actions)
[![Java CI](https://github.com/VasylP0/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/VasylP0/java-project-78/actions)
[![Sonar Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=VasylP0_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=VasylP0_java-project-78)
[![Sonar Coverage](https://sonarcloud.io/api/project_badges/measure?project=VasylP0_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=VasylP0_java-project-78)


## 📚 Description

This is a flexible Java validation library inspired by the "yup" JavaScript library.  
It allows you to define reusable schemas for validating different types of data — strings, numbers, maps — with a fluent API.

## ⚙️ Features

- Validate **strings**, **numbers**, and **maps**
- Chain multiple validations: `required()`, `minLength()`, `positive()`, etc.
- Support for **map shape validation** (`shape()` method)
- Designed with extensibility and testability in mind

## 🚀 Usage Example

```java
import hexlet.code.Validator.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema.NumberSchema;
import hexlet.code.schemas.MapSchema;

var v = new Validator();

// String validation
StringSchema schema = v.string();
schema.required().minLength(3).contains("abc");

schema.isValid("abcde"); // true
schema.isValid("ab");    // false

// Number validation
NumberSchema numberSchema = v.number();
numberSchema.required().positive().range(5, 10);

numberSchema.isValid(7);  // true
numberSchema.isValid(-1); // false

// Map validation with shape
MapSchema mapSchema = v.map();
Map<String, BaseSchema<?>> shape = Map.of(
    "name", v.string().required(),
    "age", v.number().required().positive()
);
mapSchema.shape(shape);

Map<String, Object> person = Map.of("name", "John", "age", 30);
mapSchema.isValid(person); // true



