# Ray Tracing in One Weekend - Java Implementation

## Overview

This project is a Java implementation of the concepts presented in *Ray Tracing in One Weekend* by Peter Shirley. The goal is to implement a simple ray tracer that can render 3D scenes, including basic geometry (spheres), shading, reflections, and refractions. The project follows the steps outlined in the book but is adapted for Java.

### Features Implemented

- **Ray-Sphere Intersection**: The basic mechanism to trace rays and check for intersections with spheres.
- **Diffuse Shading**: Simple Lambertian reflection for diffuse surfaces (non-reflective).
- **Reflection**: Mirror-like reflective surfaces (such as metal).
- **Refraction**: Glass-like dielectric materials that bend light as it passes through.

---

## Project Setup

### Requirements

- **Java Development Kit (JDK)**: Version 8 or higher
- **IDE** (Optional but recommended): IntelliJ IDEA, Eclipse, or any Java IDE
- **Build Tool** (Optional): Maven or Gradle if you want to manage dependencies

### Steps to Build and Run

1. **Clone the repository**:

```bash
git clone https://github.com/MAK-n/RayTracerJava.git
```

2. **Navigate to the project directory**:

```bash
cd RayTracerJava
```

3. **Compile the project** (if you're not using an IDE):

```bash
javac Main.java
```

4. **Run the program**:

```bash
java Main
```

This will generate an output image in the **PPM** format (e.g., `image.ppm`). You can open this file using an image viewer that supports the PPM format, or convert it to another format (such as PNG or JPG) using a tool like [ImageMagick](https://imagemagick.org/).

---

## Current Image

![Final Image](/out/image.png)

---


## Acknowledgments

This project is based on *Ray Tracing in One Weekend* by Peter Shirley. [raytracing.github.io](https://raytracing.github.io).


---
