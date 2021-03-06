/*
MIT License

Copyright (c) 2018 Laurent BROUDOUX

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.nuuptech.Soap.Template;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laurent
 */
@RestController
@RequestMapping("/api")
public class BeerCatalogController {

   @CrossOrigin
   @RequestMapping(method = RequestMethod.GET, value = "/beer")
   public List<Beer> getBeers(
         @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
      return BeerRepository.getBeers();
   }

   @CrossOrigin
   @RequestMapping(method = RequestMethod.GET, value = "/beer/{name}")
   public Beer getBeer(
         @PathVariable("name") String name) {
      return BeerRepository.findByName(name);
   }

   @CrossOrigin
   @RequestMapping(method = RequestMethod.GET, value = "/beer/findByStatus/{status}")
   public List<Beer> getByStatus(
         @PathVariable("status") String status) {
      return BeerRepository.findByStatus(status);
   }
}
