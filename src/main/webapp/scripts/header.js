/*!
 * Bootstrap v3.3.5 (http://getbootstrap.com)
 * Copyright 2011-2016 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */

/*!
 * Generated using the Bootstrap Customizer (http://getbootstrap.com/customize/?id=51860a96baa4466dfb82cf6143d80afa)
 * Config saved to config.json and https://gist.github.com/51860a96baa4466dfb82cf6143d80afa
 */
if ("undefined" == typeof jQuery) throw new Error("Bootstrap's JavaScript requires jQuery");
+function (t) {
    "use strict";
    var e = t.fn.jquery.split(" ")[0].split(".");
    if (e[0] < 2 && e[1] < 9 || 1 == e[0] && 9 == e[1] && e[2] < 1 || e[0] > 2) throw new Error("Bootstrap's JavaScript requires jQuery version 1.9.1 or higher, but lower than version 3")
}(jQuery), +function (t) {
    "use strict";

    function e(e) {
        var r = e.attr("data-target");
        r || (r = e.attr("href"), r = r && /#[A-Za-z]/.test(r) && r.replace(/.*(?=#[^\s]*$)/, ""));
        var o = r && t(r);
        return o && o.length ? o : e.parent()
    }

    function r(r) {
        r && 3 === r.which || (t(n).remove(), t(a).each(function () {
            var o = t(this)
                , n = e(o)
                , a = {
                relatedTarget: this
            };
            n.hasClass("open") && (r && "click" == r.type && /input|textarea/i.test(r.target.tagName) && t.contains(n[0], r.target) || (n.trigger(r = t.Event("hide.bs.dropdown", a)), r.isDefaultPrevented() || (o.attr("aria-expanded", "false"), n.removeClass("open").trigger(t.Event("hidden.bs.dropdown", a)))))
        }))
    }

    function o(e) {
        return this.each(function () {
            var r = t(this)
                , o = r.data("bs.dropdown");
            o || r.data("bs.dropdown", o = new i(this)), "string" == typeof e && o[e].call(r)
        })
    }

    var n = ".dropdown-backdrop"
        , a = '[data-toggle="dropdown"]'
        , i = function (e) {
        t(e).on("click.bs.dropdown", this.toggle)
    };
    i.VERSION = "3.3.6", i.prototype.toggle = function (o) {
        var n = t(this);
        if (!n.is(".disabled, :disabled")) {
            var a = e(n)
                , i = a.hasClass("open");
            if (r(), !i) {
                "ontouchstart" in document.documentElement && !a.closest(".navbar-nav").length && t(document.createElement("div")).addClass("dropdown-backdrop").insertAfter(t(this)).on("click", r);
                var d = {
                    relatedTarget: this
                };
                if (a.trigger(o = t.Event("show.bs.dropdown", d)), o.isDefaultPrevented()) return;
                n.trigger("focus").attr("aria-expanded", "true"), a.toggleClass("open").trigger(t.Event("shown.bs.dropdown", d))
            }
            return !1
        }
    }, i.prototype.keydown = function (r) {
        if (/(38|40|27|32)/.test(r.which) && !/input|textarea/i.test(r.target.tagName)) {
            var o = t(this);
            if (r.preventDefault(), r.stopPropagation(), !o.is(".disabled, :disabled")) {
                var n = e(o)
                    , i = n.hasClass("open");
                if (!i && 27 != r.which || i && 27 == r.which) return 27 == r.which && n.find(a).trigger("focus"), o.trigger("click");
                var d = " li:not(.disabled):visible a"
                    , s = n.find(".dropdown-menu" + d);
                if (s.length) {
                    var p = s.index(r.target);
                    38 == r.which && p > 0 && p--, 40 == r.which && p < s.length - 1 && p++, ~p || (p = 0), s.eq(p).trigger("focus")
                }
            }
        }
    };
    var d = t.fn.dropdown;
    t.fn.dropdown = o, t.fn.dropdown.Constructor = i, t.fn.dropdown.noConflict = function () {
        return t.fn.dropdown = d, this
    }, t(document).on("click.bs.dropdown.data-api", r).on("click.bs.dropdown.data-api", ".dropdown form", function (t) {
        t.stopPropagation()
    }).on("click.bs.dropdown.data-api", a, i.prototype.toggle).on("keydown.bs.dropdown.data-api", a, i.prototype.keydown).on("keydown.bs.dropdown.data-api", ".dropdown-menu", i.prototype.keydown)
}(jQuery);